package com.weghst.pine.util.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-11-28 10:41
 */
public class LockFactory {

    private static LockProvider lockProvider;

    static {
        lockProvider = new LocalLockProvider();
    }

    /**
     * @param lock
     * @return
     */
    public static Lock getLock(String lock) {
        return lockProvider.getLock(lock);
    }
}
