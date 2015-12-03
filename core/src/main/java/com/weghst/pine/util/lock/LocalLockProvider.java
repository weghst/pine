package com.weghst.pine.util.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-11-28 10:54
 */
public class LocalLockProvider implements LockProvider {

    private final Map<String, LocalLock> lockMap = new HashMap<>();

    @Override
    public Lock getLock(String key) {
        synchronized (lockMap) {
            LocalLock localLock = lockMap.get(key);
            if (localLock == null) {
                localLock = new LocalLock(key);
                lockMap.put(key, localLock);
            }
            return localLock;
        }
    }

    public void removeLock(String key) {
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
