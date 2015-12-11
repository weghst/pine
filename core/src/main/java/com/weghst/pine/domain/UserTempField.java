package com.weghst.pine.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserTempField implements Serializable {

    private static final long serialVersionUID = -7494062694414583353L;

    private int uid;
    private String field;
    private String value;
    private long createdTime;
    private int survivalMillis;

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

    public int getSurvivalMillis() {
        return survivalMillis;
    }

    public void setSurvivalMillis(int survivalMillis) {
        this.survivalMillis = survivalMillis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTempField that = (UserTempField) o;
        return uid == that.uid &&
                Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, field);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("uid", uid)
                .add("field", field)
                .add("value", value)
                .add("createdTime", createdTime)
                .add("survivalMillis", survivalMillis)
                .toString();
    }
}
