package com.yao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yao.entity.Command;
import com.yao.entity.ContentWrapper;
import com.yao.entity.UserInline;
import com.yao.service.UserInlineService;
import com.yao.utils.CloseUtils;
import com.yao.utils.ConstantValues;
import com.yao.utils.ExcelUtil;
import com.yao.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("userInline")
public class UserInlineController {
    private final int pageSize = 10;
    private final int navigateNums = 5;

    @Autowired
    private UserInlineService userInlineService;


    @RequestMapping("findAllUser/{pageNum}")
    @ResponseBody
    public String findAllUser(@PathVariable("pageNum") int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<UserInline>userInlines = userInlineService.findAllUser();
        PageInfo<UserInline> pageInfo = new PageInfo<>(userInlines,navigateNums);
        ContentWrapper<UserInline>wrapper = new ContentWrapper<>(userInlines,pageInfo);
        Gson gson = new Gson();
        return gson.toJson(wrapper);
    }

    @RequestMapping("forbidden/{id}")
    @ResponseBody
    public String forbidden(@PathVariable("id") int id){
        //1.更改数据库，将其认证状态变为非认证
        UserInline userInline = userInlineService.findUserById(id);
        userInline.setCertified(UserInline.UNCERTIFIED_USER);
        userInlineService.updateUser(userInline);
        /*//2.发送socket指令，强制退出
        if (sendSocket(userInline)){
            return "ok";
        }*/
//        return "error";
        return "ok";
    }

    @RequestMapping("allowUse/{id}")
    @ResponseBody
    public String allowUse(@PathVariable("id") int id){
        //1.更改数据库，将其认证状态变为非认证
        UserInline userInline = userInlineService.findUserById(id);
        userInline.setCertified(UserInline.CERTIFIED_USER);
        userInlineService.updateUser(userInline);
        /*//2.发送socket指令，强制退出
        if (sendSocket(userInline)){
            return "ok";
        }*/
//        return "error";
        return "ok";
    }

    @RequestMapping("findUserByUserId")
    @ResponseBody
    public String findUserByUserId(String userId,String version){
        UserInline userInline = userInlineService.findUserByUserId(userId);
        Command command;
        if (userInline==null){
            userInline = new UserInline(userId,version,TimeUtils.dateToString(),UserInline.CERTIFIED_USER);
            userInlineService.registerUser(userInline);
            command = new Command(Command.SENDMESSAGE,Command.MESSAGE_NOTHING,userId);
        }else{
            userInline.setLastUseTime(TimeUtils.dateToString());
            userInline.setVersion(version);
            userInlineService.updateUser(userInline);
            if (userInline.getCertified()==UserInline.UNCERTIFIED_USER){
                command = new Command(Command.FORBIDDEN,Command.MESSAGE_NOTHING,userId);
            }else{
                command = new Command(Command.SENDMESSAGE,Command.MESSAGE_NOTHING,userId);
            }
        }
        userInlineService.insertUserUseTIme(userId);
        Gson gson = new Gson();
        return gson.toJson(command);
    }

    @RequestMapping("register")
    @ResponseBody
    public String register(String userId,String version){
        UserInline userInline = userInlineService.findUserByUserId(userId);
        if (userInline!=null)
            return "ok";
        userInline = new UserInline(userId,version,TimeUtils.dateToString(),UserInline.CERTIFIED_USER);
        userInlineService.registerUser(userInline);
        userInlineService.insertUserUseTIme(userId);
        return "ok";
    }

    /**
     * 到处用户时间表，格式为excel
     * @param userId
     * @return
     */
    @RequestMapping("download/{userId}")
    @ResponseBody
    public String download(@PathVariable("userId") String userId, HttpServletResponse response) throws IOException {
        List<String>userUseTimes = userInlineService.getUserUseTime(userId);
        String fileName = UUID.randomUUID().toString();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createUserInlineExcel(userUseTimes).write(os);
        }catch (Exception e){
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        //设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] bytes = new byte[1024];
            int readLen;
            while ((readLen = bis.read(bytes,0,bytes.length))!=-1){
                bos.write(bytes,0,readLen);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseUtils.close(bis);
            CloseUtils.close(bos);
        }
        return null;
    }

    /**
     * socket服务，强制退出
     * @param userInline
     */
    /*private boolean sendSocket(UserInline userInline) {
        OutputStream os = null;
        try {
            Socket socket = new Socket(ConstantValues.IP,ConstantValues.PORT);
            if (socket==null)
                return false;
            Command command = new Command(Command.FORBIDDEN,Command.MESSAGE_NOTHING,userInline.getIp());
            Gson gson = new Gson();
            String buffer = gson.toJson(command)+'\n';
            os = socket.getOutputStream();
            os.write(buffer.getBytes("utf-8"));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            CloseUtils.close(os);
        }
        return true;
    }*/
}
