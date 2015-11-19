package com.weghst.pine.util;

/**
 * @author Kevin Zou
 */
public interface Query<T> {

    /**
     *
     * @return
     */
    T getQ();

    /**
     *
     * @return
     */
    String getSort();

    /**
     *
     * @return
     */
    Order getOrder();

    /**
     *
     * @return
     */
    int getCount();

    /**
     *
     * @return
     */
    int getOffset();

    /**
     *
     */
    enum Order {
        asc, desc
    }
}
