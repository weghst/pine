package com.weghst.pine.domain;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class Config implements Serializable {

    private static final long serialVersionUID = -2534573636855113712L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(key, config.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("key", key)
                .add("value", value)
                .add("remarks", remarks)
                .add("comments", comments)
                .add("needReboot", needReboot)
                .add("lastUpdatedTime", lastUpdatedTime)
                .add("lastBut2UpdatedTime", lastBut2UpdatedTime)
                .toString();
    }
}
