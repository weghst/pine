package com.weghst.pine.util.lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-11-28 12:46
 */
public class DistributedLockProvider implements LockProvider {

    private ZooKeeper zooKeeper;

    public DistributedLockProvider() throws IOException {
        zooKeeper = new ZooKeeper("localhost:9090", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // FIXME 待处理
            }
        });

    }

    @Override
    public Lock getLock(String key) {
        return null;
    }

    private class DistributedLock implements Lock {

        @Override
        public void lock() {

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
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }
}
