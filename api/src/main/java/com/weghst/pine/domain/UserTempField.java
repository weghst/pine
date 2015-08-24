package com.weghst.pine.domain;

import java.io.Serializable;

public class UserTempField implements Serializable {

    private int uid;
    private String field;
    private String value;
    private long createdTime;
    private long survivalMillis;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getSurvivalMillis() {
        return survivalMillis;
    }

    public void setSurvivalMillis(long survivalMillis) {
        this.survivalMillis = survivalMillis;
    }

}
