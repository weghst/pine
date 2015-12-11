package com.weghst.pine.util.lock;

import java.util.concurrent.locks.Lock;

/**
 * 同步锁提供者接口定义.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface LockProvider {

    /**
     * 返回同步锁实例.
     *
     * @param path 同步路径
     * @return 同步锁
     */
    Lock getLock(String path);

}
