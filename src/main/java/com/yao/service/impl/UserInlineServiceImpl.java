package com.yao.service.impl;

import com.yao.dao.UserInlineMapper;
import com.yao.entity.UserInline;
import com.yao.service.UserInlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInlineServiceImpl implements UserInlineService {

    @Autowired
    private UserInlineMapper mapper;

    @Override
    public UserInline findUserById(int id) {
        UserInline userInline = mapper.findUserById(id);
        return userInline;
    }

    @Override
    public UserInline findUserByIp(String ip) {
        UserInline userInline = mapper.findUserByIp(ip);
        return userInline;
    }

    @Override
    public void updateUser(UserInline userInline) {
        mapper.updateUser(userInline);
    }

    @Override
    public List<UserInline> findUserByState(int state) {
        List<UserInline> userInlines = mapper.findUserByState(state);
        return userInlines;
    }

    @Override
    public List<UserInline> findUserByCertified(int certified) {
        List<UserInline> userInlines = mapper.findUserByCertified(certified);
        return userInlines;
    }

    @Override
    public List<UserInline> findAllUser() {
        List<UserInline>userInlines = mapper.findAllUser();
        return userInlines;
    }

    @Override
    public void registerUser(UserInline user) {
        mapper.insertUser(user);
    }
}
