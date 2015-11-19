package com.weghst.pine.util;

/**
 * @author Kevin Zou
 */
public abstract class AbstractQuery<T> implements Query<T> {

    private String sort;
    private Order order = Order.asc;
    private int count;
    private int offset;

    @Override
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
