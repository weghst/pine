package com.weghst.pine.web.vo;

import com.weghst.pine.util.Query;

import javax.ws.rs.QueryParam;

/**
 * @author Kevin Zou
 */
public class SimpleQueryVo implements Query {

    @QueryParam("q")
    private String q;
    @QueryParam("sort")
    private String sort;
    @QueryParam("order")
    private String order;
    @QueryParam("page")
    private int page;

    @Override
    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
