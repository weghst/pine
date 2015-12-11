package com.weghst.pine.util;

import org.springframework.util.Assert;

/**
 * 简单搜索/查询类定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public final class SimpleQuery extends AbstractQuery<String> {

    private String q;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getQ() {
        return q;
    }

    /**
     * 设置搜索/查询关键字.
     *
     * @param q 搜索/查询关键字
     */
    public void setQ(String q) {
        Assert.hasLength(q);
        this.q = q;
    }
}
