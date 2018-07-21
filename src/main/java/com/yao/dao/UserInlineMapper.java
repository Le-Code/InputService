package com.yao.dao;

import com.yao.entity.UserInline;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface UserInlineMapper {
    /**
     * 按照id查找
     * @param id
     * @return
     */
    UserInline findUserById(int id);

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
     * 按照userId进行查找
     * @param userId
     * @return
     */
    UserInline findUserByUserId(String userId);

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

    /**
     * 得到一个用户的使用时间
     * @param userId
     * @return
     */
    List<String>getUserUseTime(String userId);

    /**
     * 添加用户的一个使用时间
     * @param userId
     * @return
     */
    Integer insertUseTime(@Param("userId") String userId, @Param("curTime") String curTime);
}
