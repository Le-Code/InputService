package com.yao.service.impl;

import com.yao.dao.UserInlineMapper;
import com.yao.entity.UserInline;
import com.yao.service.UserInlineService;
import com.yao.utils.TimeUtils;
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
    public UserInline findUserByUserId(String userId) {
        UserInline userInline = mapper.findUserByUserId(userId);
        return userInline;
    }

    @Override
    public void updateUser(UserInline userInline) {
        mapper.updateUser(userInline);
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

    /**
     * 查找某个用户的所有时间
     * @param userId
     * @return
     */
    @Override
    public List<String> getUserUseTime(String userId) {
        return mapper.getUserUseTime(userId);
    }

    @Override
    public Integer insertUserUseTIme(String userId) {
        String curTime = TimeUtils.dateToString();
        return mapper.insertUseTime(userId,curTime);
    }
}
