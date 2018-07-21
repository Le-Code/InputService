package com.yao.entity;

public class IMEVersion {
    private int id;
    private String version;
    private String path;
    private String uploadTime;

    public IMEVersion() {
    }

    public IMEVersion(String version, String path, String uploadTime) {
        this.version = version;
        this.path = path;
        this.uploadTime = uploadTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "IMEVersion{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", path='" + path + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
