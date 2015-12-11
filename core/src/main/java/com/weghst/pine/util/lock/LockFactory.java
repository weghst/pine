package com.weghst.pine.util.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Lock;

/**
 * 获取同步锁工厂类定义.
 * <p>通过配置{@code META-INF/services/com.weghst.pine.util.lock.LockProvider}锁提供者,
 * 如果未配置则默认使用{@code LocalLockProvider}.</p>
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LockFactory {

    private static final Logger LOG = LoggerFactory.getLogger(LockFactory.class);

    private static LockProvider lockProvider;

    static {
        ServiceLoader<LockProvider> serviceLoader = ServiceLoader.load(LockProvider.class);
        Iterator<LockProvider> it = serviceLoader.iterator();
        if (it.hasNext()) {
            lockProvider = it.next();

            LOG.debug("加载LockProvider -> {}", lockProvider.getClass());
        }

        if (lockProvider == null) {
            lockProvider = new LocalLockProvider();
        }
    }

    /**
     * 返回同步锁.
     * <p>根据同步路径(关键字)来获取同步锁实例</p>
     *
     * @param path 同步路径
     * @return 同步锁
     */
    public static Lock getLock(String path) {
        return lockProvider.getLock(path);
    }
}
