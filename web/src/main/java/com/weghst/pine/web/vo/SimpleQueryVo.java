package com.weghst.pine.web.vo;

import com.weghst.pine.util.SimpleQuery;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class SimpleQueryVo extends SimpleQuery {

    @QueryParam("q")
    @Override
    public void setQ(String q) {
        super.setQ(q);
    }

    @QueryParam("sort")
    @Override
    public void setSort(String sort) {
        super.setSort(sort);
    }

    @QueryParam("order")
    @Override
    public void setOrder(Order order) {
        super.setOrder(order);
    }

    @QueryParam("page")
    @Override
    public void setPage(int page) {
        super.setPage(page);
    }

    @QueryParam("pageSize")
    @Override
    public void setPageSize(int pageSize) {
        super.setPageSize(pageSize);
    }
}
