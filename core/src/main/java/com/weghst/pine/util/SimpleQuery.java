package com.weghst.pine.util;

/**
 * @author Zou Yong
 */
public class SimpleQuery extends AbstractQuery<String> {

    private String q;

    @Override
    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
