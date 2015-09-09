package com.weghst.pine.util;

/**
 * @author Kevin Zou
 */
public interface Query {

    /**
     *
     * @return
     */
    String getQ();

    /**
     *
     * @return
     */
    String getSort();

    /**
     *
     * @return
     */
    String getOrder();

    /**
     *
     * @return
     */
    int getPage();
}
