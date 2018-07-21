package com.yao.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class UploadUtil {

    /**
     * 上传文件
     * @param request
     * @param path 保存文件的路径
     * @return 文件的名称
     * @throws IOException
     */
    public static String upload(HttpServletRequest request,String path) throws IOException {
        //将当前上下文初始化给commonMultipartResolver(多部分解析器)
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String originFile = null;
        String realPath = request.getSession().getServletContext().getRealPath(path);
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multipartHttpServletRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
                //上传
                File f = new File(realPath);
                if (!f.exists())
                    f.mkdirs();
                if (file != null) {
                    originFile = file.getOriginalFilename();
                    String savePath = realPath + File.separator + originFile;
                    file.transferTo(new File(savePath));
                }
                break;
            }
        }
        return originFile;
    }
}
