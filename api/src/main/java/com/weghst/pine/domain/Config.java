package com.weghst.pine.domain;

public class Config {

    private String key;
    private String value;
    private String remarks;
    private String comments;
    private boolean needReboot;
    private long lastUpdatedTime;
    private long lastBut2UpdatedTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isNeedReboot() {
        return needReboot;
    }

    public void setNeedReboot(boolean needReboot) {
        this.needReboot = needReboot;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getLastBut2UpdatedTime() {
        return lastBut2UpdatedTime;
    }

    public void setLastBut2UpdatedTime(long lastBut2UpdatedTime) {
        this.lastBut2UpdatedTime = lastBut2UpdatedTime;
    }

}
