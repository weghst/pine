package com.weghst.pine.util;

/**
 * 搜索/查询接口属性定义.
 *
 * @param <T> 搜索/查询条件参数类型
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface Query<T> {

    /**
     * 返回搜索/查询条件.
     *
     * @return
     */
    T getQ();

    /**
     * 返回排序属性名称.
     *
     * @return 排序属性名称
     */
    String getSort();

    /**
     * 返回排序方式.
     *
     * @return 排序方式
     */
    Order getOrder();

    /**
     * 返回搜索页码.
     *
     * @return 页码
     */
    int getPage();

    /**
     * 返回当前页最大记录数.
     *
     * @return 记录数
     */
    int getPageSize();

    /**
     * 搜索/查询排序方式.
     */
    enum Order {
        /**
         * 正序.
         */
        asc,
        /**
         * 倒序.
         */
        desc
    }
}
