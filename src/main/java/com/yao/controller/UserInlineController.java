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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

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
        userInline.setCertified(0);
        userInlineService.updateUser(userInline);
        /*//2.发送socket指令，强制退出
        if (sendSocket(userInline)){
            return "ok";
        }*/
//        return "error";
        return "ok";
    }

    @RequestMapping("findUserByIp")
    @ResponseBody
    public String findUserByIp(String ip){
        UserInline userInline = userInlineService.findUserByIp(ip);
        Command command;
        if (userInline==null){
            userInline = new UserInline(ip,UserInline.STATE_INLINE,UserInline.CERTIFIED_USER);
            userInlineService.registerUser(userInline);
            command = new Command(Command.SENDMESSAGE,Command.MESSAGE_NOTHING,ip);
        }else{
            userInline.setState(UserInline.STATE_INLINE);
            userInlineService.updateUser(userInline);
            if (userInline.getCertified()==UserInline.UNCERTIFIED_USER){
                command = new Command(Command.FORBIDDEN,Command.MESSAGE_NOTHING,ip);
            }else{
                command = new Command(Command.SENDMESSAGE,Command.MESSAGE_NOTHING,ip);
            }
        }
        Gson gson = new Gson();
        return gson.toJson(command);
    }

    @RequestMapping("register")
    @ResponseBody
    public String register(String ip){
        UserInline userInline = userInlineService.findUserByIp(ip);
        if (userInline!=null)
            return "ok";
        userInline = new UserInline(ip,UserInline.STATE_INLINE,UserInline.CERTIFIED_USER);
        userInlineService.registerUser(userInline);
        return "ok";
    }

    /**
     * socket服务，强制退出
     * @param userInline
     */
    private boolean sendSocket(UserInline userInline) {
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
    }
}
