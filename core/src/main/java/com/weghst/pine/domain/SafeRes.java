package com.weghst.pine.domain;

import java.io.Serializable;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SafeRes implements Serializable {

    private int id;
    private String url;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
