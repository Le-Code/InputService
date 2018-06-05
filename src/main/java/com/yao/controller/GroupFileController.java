package com.yao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yao.entity.ContentWrapper;
import com.yao.entity.CustomGroup;
import com.yao.entity.CustomPageInfo;
import com.yao.entity.GroupFileInfo;
import com.yao.service.GroupInfoService;
import com.yao.utils.CloseUtils;
import com.yao.utils.TimeUtils;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PinyinTagging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping(value = "group")
public class GroupFileController {

    /**
     * 每一页显示10条信息
     */
    private final int pageSize = 10;
    private final int navigatesPages = 5;
    private final String root = "upload"+File.separator+"group"+ File.separator;

    private String originFilePath;

    private Set<CustomGroup> manuallyList = new HashSet<>();
    private List<CustomGroup>fileList = new ArrayList<>();

    @Autowired
    private GroupInfoService groupInfoService;

    @RequestMapping(value = "findAll")
    @ResponseBody
    public String findAll(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "null") String orderRule, Model model){
        PageHelper.startPage(pageNum,pageSize);
        Gson gson = new Gson();
        if (orderRule.equals("null"))
            orderRule = null;
        List<GroupFileInfo> list = groupInfoService.findAllGroupFile(orderRule);
        PageInfo pageInfo = new PageInfo(list,5);
        ContentWrapper<GroupFileInfo> content = new ContentWrapper<>(list,pageInfo);
        String json = gson.toJson(content);
        return json;
    }

    @RequestMapping(value = "upload",produces = {"text/html;charset=UTF-8;"})
    @ResponseBody
    public String uploadFile(HttpServletRequest request) throws IOException {
        //将当前上下文初始化给commonMultipartResolver(多部分解析器)
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String result = "";
        String realPath = request.getSession().getServletContext().getRealPath(root);
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multipartHttpServletRequest.getFileNames();
            while (iter.hasNext()){
                MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
                //上传
                File f = new File(realPath);
                if (!f.exists())
                    f.mkdirs();
                if (file!=null){
                    String originFile = file.getOriginalFilename();
                    originFilePath = System.currentTimeMillis()+originFile.substring(originFile.lastIndexOf('.'));
                    String path = realPath+File.separator+originFilePath;
                    file.transferTo(new File(path));
                    CustomPageInfo<CustomGroup>pageInfo = readFileReturnPageInfo(path);
                    if (pageInfo!=null){
                        Gson gson = new Gson();
                        result = gson.toJson(pageInfo);
                    }
                }
                break;
            }
        }
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "requestForOtherPage/{pageNum}",produces = {"text/html;charset=UTF-8;"})
    @ResponseBody
    public String requestForOtherPage(@PathVariable("pageNum") int pageNum){
        CustomPageInfo<CustomGroup>pageInfo = new CustomPageInfo<>(fileList,pageSize);
        pageInfo.split(fileList,pageNum,navigatesPages);
        Gson gson = new Gson();
        return gson.toJson(pageInfo);
    }

    @RequestMapping(value = "deleteFileItem",produces = {"text/html;charset=UTF-8;"})
    @ResponseBody
    public String deleteFileItem(int pageNum,int itemId){
        fileList.remove(itemId);
        CustomPageInfo<CustomGroup>pageInfo = new CustomPageInfo<>(fileList,pageSize);
        pageInfo.split(fileList,pageNum,navigatesPages);
        Gson gson = new Gson();
        return gson.toJson(pageInfo);
    }

    @RequestMapping(value = "saveFileItem",produces = {"text/html;charset=UTF-8;"})
    @ResponseBody
    public String saveFileItem(String pinyin,String translate,int pageNum,int itemId){
        System.out.println(pinyin+":"+translate);
        CustomGroup group = fileList.get(itemId);
        group.setPinyin(pinyin.replaceAll(" ","'"));group.setTranslate(translate);group.setConfirm(true);
        CustomPageInfo<CustomGroup>pageInfo = new CustomPageInfo<>(fileList,pageSize);
        pageInfo.split(fileList,pageNum,navigatesPages);
        Gson gson = new Gson();
        return gson.toJson(pageInfo);
    }

    @RequestMapping("saveManuallySingle")
    @ResponseBody
    public String saveManuallySingle(String chinese,String pinyin,String translate){
        manuallyList.add(new CustomGroup(chinese,pinyin,translate,false));
        return "success";
    }


    @RequestMapping("saveGroupFile")
    public String saveGroupFile(String groupName,String originFile,String groupDesc,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath(root);
        String groupPath = System.currentTimeMillis()+".txt";
        GroupFileInfo info = new GroupFileInfo();
        info.setAuthor("admin");
        info.setCreateTime(TimeUtils.dateToString());
        info.setGroupPath(groupPath);
        info.setGroupDesc(groupDesc);
        info.setGroupName(groupName);
        info.setOriginFile(originFile);
        info.setGroupNum(fileList.size());
        info.setOriginFilePath(originFilePath);
        writeInfoFile(realPath+File.separator+groupPath);
        groupInfoService.addGroupFile(info);
        fileList.clear();
        originFilePath = "";
        return "operate_success";
    }

    /**
     * 将列表写到文件里面去
     * @param path
     */
    private void writeInfoFile(String path) {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(path));
            for (CustomGroup group:fileList){
                writer.write(group.getChinese()+"@"+group.getPinyin()+"@"+group.getTranslate());
                writer.newLine();
                writer.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseUtils.close(writer);
        }
    }

    /**
     * 读取文件,并进行分词
     * @param fileName
     * @return
     * @throws IOException
     */
    private CustomPageInfo<CustomGroup> readFileReturnPageInfo(String fileName) throws IOException {
        fileList.clear();
        File file = new File(fileName);
        if (!file.exists()) return null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str;
        String content = "";
        while ((str = reader.readLine())!=null){
            content+=str+"。";
        }
        fileList.addAll(splitStr(content));
        CustomPageInfo<CustomGroup>pageInfo = new CustomPageInfo<>(fileList,pageSize);
        pageInfo.split(fileList,1,navigatesPages);
        return pageInfo;
    }

    /**
     * 对字符串进行分词
     * @param str
     * @return
     */
    public Set<CustomGroup> splitStr(String str){
        List<Word> words = WordSegmenter.segWithStopWords(str);
        PinyinTagging.process(words);
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        Set<CustomGroup>set = new HashSet<>();
        for (Word word:words){
            sb.setLength(0);
            String suoxie = word.getAcronymPinYin();
            String pinyin = word.getFullPinYin();
            char[]chars = suoxie.toCharArray();
            for (int i = chars.length-1;i>=1;i--){
                stack.push(pinyin.substring(pinyin.lastIndexOf(chars[i])));
                pinyin = pinyin.substring(0,pinyin.lastIndexOf(chars[i]));
            }
            stack.push(pinyin);
            sb.append(stack.pop());
            if (sb.length()==0||word.getText().length()<2)
                continue;
            while (!stack.isEmpty()){
                sb.append("'"+stack.pop());
            }
            set.add(new CustomGroup(word.getText(),sb.toString(),"",false));
        }
        return set;
    }
}
