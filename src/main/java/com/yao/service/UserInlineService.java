package com.yao.service;

import com.yao.entity.UserInline;

import java.util.List;

public interface UserInlineService {
    UserInline findUserById(int id);
    UserInline findUserByUserId(String userId);
    void updateUser(UserInline userInline);
    List<UserInline>findUserByCertified(int certified);
    List<UserInline>findAllUser();
    void registerUser(UserInline user);

    List<String>getUserUseTime(String userId);
    Integer insertUserUseTIme(String userId);
}
