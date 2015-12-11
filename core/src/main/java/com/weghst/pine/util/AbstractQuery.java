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
    private int count;
    private int offset;

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getSort() {
        return sort;
    }

    /**
     * 设置排序字段名称.
     *
     * @param sort 排序字段名称
     */
    public final void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Order getOrder() {
        return order;
    }

    /**
     * 设置排序方式, 默认值为{@link com.weghst.pine.util.Query.Order#asc}.
     *
     * @param order 排序方式
     */
    public final void setOrder(Order order) {
        Assert.notNull(order);
        this.order = order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getCount() {
        return count;
    }

    /**
     * 设置返回结果集记录数, 必须是一个正整数.
     *
     * @param count 结果集记录数
     */
    public final void setCount(int count) {
        Assert.isTrue(count > 0);
        this.count = count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getOffset() {
        return offset;
    }

    /**
     * 设置返回结果集偏移量, 必须是一个大于等于0的数字.
     *
     * @param offset 结果集偏移量
     */
    public final void setOffset(int offset) {
        Assert.isTrue(offset >= 0);
        this.offset = offset;
    }
}
