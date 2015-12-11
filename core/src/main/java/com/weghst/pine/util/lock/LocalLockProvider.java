package com.weghst.pine.util.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本地同步锁提供者, 只对当前进程有效.
 *
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class LocalLockProvider implements LockProvider {

    private final Map<String, LocalLock> lockMap = new HashMap<>();

    @Override
    public Lock getLock(String path) {
        synchronized (lockMap) {
            LocalLock localLock = lockMap.get(path);
            if (localLock == null) {
                localLock = new LocalLock(path);
                lockMap.put(path, localLock);
            }
            return localLock;
        }
    }

    private void removeLock(String key) {
        synchronized (lockMap) {
            lockMap.remove(key);
        }
    }

    private class LocalLock implements Lock {

        final ReentrantLock lock = new ReentrantLock();
        final AtomicInteger count = new AtomicInteger();
        final String key;

        LocalLock(String key) {
            this.key = key;
        }

        @Override
        public void lock() {
            count.incrementAndGet();

            lock.lock();
        }

        @Override
        public void unlock() {
            lock.unlock();

            if (count.decrementAndGet() <= 0) {
                removeLock(key);
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
