package com.yao.entity;

public class UserInline {


    public static final int STATE_INLINE = 1;
    public static final int STATE_OFFLINE = 0;
    public static final int CERTIFIED_USER = 1;
    public static final int UNCERTIFIED_USER = 0;

    private int id;
    /**
     * 在线用户ip
     */
    private String ip;
    /**
     * 状态
     */
    private int state;
    /**
     * 是否认证
     * 默认一开始注册都是认证用户
     */
    private int certified;

    public UserInline() {
    }

    public UserInline(String ip, int state, int certified) {
        this.id = id;
        this.ip = ip;
        this.state = state;
        this.certified = certified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCertified() {
        return certified;
    }

    public void setCertified(int certified) {
        this.certified = certified;
    }
}
