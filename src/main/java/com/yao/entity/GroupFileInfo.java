package com.yao.entity;

public class GroupFileInfo {
    /**
     * 词库文件id
     */
    private int id;
    /**
     * 词库名
     */
    private String groupName;
    /**
     * 词库文件名
     */
    private String groupPath;
    /**
     * 文件创建时间
     */
    private String createTime;
    /**
     * 文件创建者
     */
    private String author;
    /**
     * 词的数量
     */
    private long groupNum;
    /**
     * 来源文件的名称
     */
    private String originFile;
    /**
     * 来源文件的路径
     */
    private String originFilePath;
    /**
     * 词库的描述
     */
    private String groupDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(long groupNum) {
        this.groupNum = groupNum;
    }

    public String getOriginFile() {
        return originFile;
    }

    public void setOriginFile(String originFile) {
        this.originFile = originFile;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupPath() {
        return groupPath;
    }

    public void setGroupPath(String groupPath) {
        this.groupPath = groupPath;
    }

    public String getOriginFilePath() {
        return originFilePath;
    }

    public void setOriginFilePath(String originFilePath) {
        this.originFilePath = originFilePath;
    }
}
