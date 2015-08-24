package com.weghst.pine.domain;

import java.util.Set;

public class SafeResGroup {

    private int id;
    private String name;
    private Set<SafeRes> safeResSet;
    private String remarks;
    private long createdTime;
    private long lastUpdatedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SafeRes> getSafeResSet() {
        return safeResSet;
    }

    public void setSafeResSet(Set<SafeRes> safeResSet) {
        this.safeResSet = safeResSet;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
