package com.weghst.pine.util;

import org.springframework.util.Assert;

/**
 * 搜索搜索/查询类定义.
 *
 * @param <T> 搜索/查询参数类型
 * @author Kevin Zou (kevinz@weghst.com)
 */
public abstract class AbstractQuery<T> implements Query<T> {

    private String sort;
    private Order order = Order.asc;
    private int page;
    private int pageSize;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSort() {
        return sort;
    }

    /**
     * 设置排序字段名称.
     *
     * @param sort 排序字段名称
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Order getOrder() {
        return order;
    }

    /**
     * 设置排序方式, 默认值为{@link com.weghst.pine.util.Query.Order#asc}.
     *
     * @param order 排序方式
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * 设置当前页码, 必须是一个大于等于0的数字.
     *
     * @param page 页码
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数, 必须是一个正整数.
     *
     * @param pageSize 记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "AbstractQuery{" +
                "sort='" + sort + '\'' +
                ", order=" + order +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
