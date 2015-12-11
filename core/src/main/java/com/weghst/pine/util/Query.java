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
     * 返回结果集最大记录数.
     *
     * @return 结果集长度
     */
    int getCount();

    /**
     * 返回搜索/查询结果集偏移量.
     *
     * @return 结果集偏移量
     */
    int getOffset();

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
