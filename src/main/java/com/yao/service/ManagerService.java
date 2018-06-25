package com.yao.service;

public interface ManagerService {
    /**
     * 登陆服务
     * @param username
     * @param password
     * @return
     */
    boolean doLogin(String username,String password);
}
