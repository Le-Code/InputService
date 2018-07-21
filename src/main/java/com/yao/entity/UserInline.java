package com.yao.entity;

public class UserInline {

    public static final int CERTIFIED_USER = 1;
    public static final int UNCERTIFIED_USER = 0;

    private int id;
    /**
     * 在线用户ip
     */
    private String userId;
    /**
     * 最近一次使用时间
     */
    private String lastUseTime;
    /**
     * 是否认证
     * 默认一开始注册都是认证用户
     */
    private Integer certified;

    /**
     * 使用的版本
     */
    private String version;

    public UserInline() {
    }

    public UserInline(String userId, String version,String lastUseTime, int certified) {
        this.userId = userId;
        this.version = version;
        this.lastUseTime = lastUseTime;
        this.certified = certified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(String lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public Integer getCertified() {
        return certified;
    }

    public void setCertified(Integer certified) {
        this.certified = certified;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
