package com.weghst.pine.util;

import java.util.List;

/**
 *
 * @param <T>
 */
public class Paging<T> {

    public static final int DEFAULT_SIZE = 10;
    public static final int DEFAULT_PAGE = 1;

    private int size;
    private int page = DEFAULT_PAGE;

    // out param
    private int total;
    private List<T> result;

    /**
     *
     * @param page
     */
    public Paging(int page) {
        this(DEFAULT_SIZE, page);
    }

    /**
     *
     * @param size
     * @param page
     */
    public Paging(int size, int page) {
        setSize(size);
        setPage(page);
    }

    /**
     *
     * @param q
     */
    public Paging(Query q) {
        this(q.getOffset());
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size
     * @return
     */
    public Paging setSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("[size]必须大于1");
        }
        this.size = size;
        return this;
    }

    /**
     *
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     *
     * @param page
     * @return
     */
    public Paging setPage(int page) {
        if (page < 1) {
            throw new IllegalArgumentException("[page]必须大于1");
        }
        this.page = page;
        return this;
    }

    /**
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * @return
     */
    public Paging setTotal(int total) {
        this.total = total;
        return this;
    }

    /**
     *
     * @return
     */
    public List<T> getResult() {
        return result;
    }

    /**
     *
     * @param result
     * @return
     */
    public Paging setResult(List<T> result) {
        this.result = result;
        return this;
    }

    /**
     *
     * @return
     */
    public int getMaxPage() {
        int maxPage = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            maxPage += 1;
        }
        return maxPage;
    }

    /**
     *
     * @return
     */
    public int getStartingIndex() {
        return ((getPage() - 1) * getSize());
    }

    /**
     *
     * @return
     */
    public int getEndingIndex() {
        return (getPage() * getSize());
    }

    /**
     *
     * @return
     */
    public int getPreviousPage() {
        int p = (page - 1);
        if (p <= 0) {
            return 1;
        }
        return p;
    }

    /**
     *
     * @return
     */
    public int getNextPage() {
        return (page + 1);
    }

    /**
     *
     * @return
     */
    public boolean isPrevious() {
        return (getPreviousPage() > DEFAULT_PAGE);
    }

    /**
     *
     * @return
     */
    public boolean isNext() {
        return (getNextPage() < getMaxPage());
    }

}
