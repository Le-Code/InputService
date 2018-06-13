package com.yao.dao;

import com.yao.entity.UserInline;

import java.util.List;

public interface UserInlineMapper {
    /**
     * 按照id查找
     * @param id
     * @return
     */
    UserInline findUserById(int id);

    /**
     * 按照状态查找
     * @param state
     * @return
     */
    List<UserInline>findUserByState(int state);

    /**
     * 按照是否认证进行查找
     * @param certified
     * @return
     */
    List<UserInline>findUserByCertified(int certified);

    /**
     * 查找所有的用户
     * @return
     */
    List<UserInline>findAllUser();

    /**
     * 按照ip进行查找
     * @param ip
     * @return
     */
    UserInline findUserByIp(String ip);

    /**
     * 更新user
     * @param user
     */
    void updateUser(UserInline user);

    /**
     * 插入一条数据
     * @param user
     */
    void insertUser(UserInline user);
}
