package com.yao.test;

import com.yao.dao.UserInlineMapper;
import com.yao.entity.UserInline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class TestUserInline {
    @Autowired
    private UserInlineMapper mapper;

    @Test
    public void findUserById(){
        UserInline userInline = mapper.findUserById(4);
        System.out.println(userInline);
    }

    @Test
    public void findUserByIp(){
        UserInline userInline = mapper.findUserByIp("192.168.2.103");
        System.out.println(userInline);
    }

    @Test
    public void findUserByState(){
        List<UserInline>users = mapper.findUserByState(1);
        System.out.println(users.size());
    }

    @Test
    public void findUserByCertified(){
        List<UserInline>users = mapper.findUserByCertified(1);
        System.out.println(users.size());
    }

    @Test
    public void updateUser(){
        UserInline userInline = mapper.findUserById(4);
        userInline.setCertified(0);
        mapper.updateUser(userInline);
    }

    @Test
    public void f() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
    }
}
