package com.yao.service.impl;

import com.yao.dao.ManagerMapper;
import com.yao.entity.CustomManager;
import com.yao.service.ManagerService;
import com.yao.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public boolean doLogin(String username, String password) {
        //获得输入密码的密文
        String pass_md5 = MD5Util.getMD5(password);
        CustomManager manager = managerMapper.findManager(username,pass_md5);
        if (manager==null)
            return false;
        return true;
    }
}
