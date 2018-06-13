package com.yao.service;

import com.yao.entity.UserInline;

import java.util.List;

public interface UserInlineService {
    UserInline findUserById(int id);
    UserInline findUserByIp(String ip);
    void updateUser(UserInline userInline);
    List<UserInline>findUserByState(int state);
    List<UserInline>findUserByCertified(int certified);
    List<UserInline>findAllUser();
    void registerUser(UserInline user);
}
