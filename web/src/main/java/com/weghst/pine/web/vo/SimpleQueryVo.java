package com.weghst.pine.web.vo;

import com.weghst.pine.util.SimpleQuery;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SimpleQueryVo extends SimpleQuery {

    @Override
    public void setQ(String q) {
        super.setQ(q);
    }

    @Override
    public void setSort(String sort) {
        super.setSort(sort);
    }

    @Override
    public void setOrder(Order order) {
        super.setOrder(order);
    }

    @Override
    public void setPage(int page) {
        super.setPage(page);
    }

    @Override
    public void setPageSize(int pageSize) {
        super.setPageSize(pageSize);
    }
}
