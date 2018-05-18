package com.yao.entity;

/**
 * 可见的反馈
 */
public class CourseFeedback {
    private int id;//反馈id
    private String info;//反馈内容
    private String feedTime;//反馈时间
    private int isRead;//是否已经阅读过了

    public CourseFeedback(int id, String info, String feedTime, int isRead) {
        this.id = id;
        this.info = info;
        this.feedTime = feedTime;
        this.isRead = isRead;
    }

    public CourseFeedback(String info, String feedTime, int isRead) {
        this.info = info;
        this.feedTime = feedTime;
        this.isRead = isRead;
    }

    public CourseFeedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getfeedTime() {
        return feedTime;
    }

    public void setfeedTime(String feedTime) {
        this.feedTime = feedTime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "CourseFeedBack{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", feedTime='" + feedTime + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
