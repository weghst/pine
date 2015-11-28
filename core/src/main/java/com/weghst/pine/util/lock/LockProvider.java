package com.weghst.pine.util.lock;

import java.util.concurrent.locks.Lock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-11-28 10:52
 */
public interface LockProvider {

    /**
     * @param key
     * @return
     */
    Lock getLock(String key);

}
