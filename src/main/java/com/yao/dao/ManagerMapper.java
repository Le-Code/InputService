package com.yao.dao;

import com.yao.entity.CustomManager;
import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {
    /**
     * 根据用户名和密码进行查找
     * @param username
     * @param password
     * @return
     */
    CustomManager findManager(@Param("username") String username, @Param("password") String password);
}
