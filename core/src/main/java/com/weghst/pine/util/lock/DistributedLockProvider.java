package com.weghst.pine.util.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-11-28 12:46
 */
public class DistributedLockProvider implements LockProvider {

    private CuratorFramework curatorFramework;

    /**
     * @param connectStr
     */
    public DistributedLockProvider(String connectStr) {
        curatorFramework = CuratorFrameworkFactory.newClient(connectStr, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
    }

    @Override
    public Lock getLock(String path) {
        if (path == null || path.length() == 0) {
            throw new IllegalArgumentException("参数[path]必须是长度大于0的字符串");
        }

        DistributedLock lock = new DistributedLock(normalizePath(path));
        return lock;
    }

    private String normalizePath(String path) {
        if (path.charAt(0) != '/') {
            return "/" + path;
        }
        return path;
    }

    private class DistributedLock implements Lock {

        final InterProcessMutex mutex;

        DistributedLock(String path) {
            mutex = new InterProcessMutex(curatorFramework, path);
        }

        @Override
        public void lock() {
            try {
                mutex.acquire();
            } catch (Exception e) {
                throw new LockException("InterProcessMutex acquire", e);
            }
        }

        @Override
        public void unlock() {
            try {
                mutex.release();
            } catch (Exception e) {
                throw new LockException("InterProcessMutex release", e);
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet");
        }

        @Override
        public boolean tryLock() {
            throw new UnsupportedOperationException("Not supported yet");
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            throw new UnsupportedOperationException("Not supported yet");
        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }
}