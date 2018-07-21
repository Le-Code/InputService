package com.yao.controller;

import com.google.gson.Gson;
import com.yao.entity.IMEVersion;
import com.yao.service.IMEVersionService;
import com.yao.service.impl.IMEVersionServiceImpl;
import com.yao.utils.TimeUtils;
import com.yao.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.image.OffScreenImage;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("imeVersion")
public class IMEVersionControl {

    @Autowired
    private IMEVersionService imeVersionService;

    private final String root = "upload" + File.separator + "apk" + File.separator;

    @RequestMapping("add")
    @ResponseBody
    public String addNewVersion(String version, HttpServletRequest req){
        IMEVersion imeVersion = new IMEVersion();
        imeVersion.setVersion(version);imeVersion.setUploadTime(TimeUtils.dateToString());
        String path = null;
        try {
            path = UploadUtil.upload(req,root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (path==null)
            try {
                throw new IOException("上传失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
        imeVersion.setPath(path);
        imeVersionService.addNewVersion(imeVersion);
        return "ok";
    }

    @RequestMapping("checkVersion")
    @ResponseBody
    public String checkVersion(){
        IMEVersion imeVersion = imeVersionService.findLast();
        if (imeVersion==null)
            return "error";
        Gson gson = new Gson();
        String json = gson.toJson(imeVersion);
        return json;
    }
}
