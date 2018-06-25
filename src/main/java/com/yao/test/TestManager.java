package com.yao.test;

import com.yao.dao.ManagerMapper;
import com.yao.entity.CustomManager;
import com.yao.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class TestManager {

    @Autowired
    private ManagerMapper managerMapper;

    @Test
    public void testLogin(){
        String username = "iclabs";
        String password = MD5Util.getMD5("Wsn331331");
        CustomManager manager = managerMapper.findManager(username,password);
        System.out.println(manager);
    }
}
