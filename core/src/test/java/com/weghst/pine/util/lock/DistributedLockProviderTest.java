package com.weghst.pine.util.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.testng.annotations.Test;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-12-02 16:52
 */
@SuppressWarnings("ALL")
public class DistributedLockProviderTest {

    @Test
    public void testGetLock() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181", new RetryPolicy() {
            @Override
            public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
                try {
                    sleeper.sleepFor(1, TimeUnit.SECONDS);
                    return true;
                } catch (InterruptedException e) {
                }
                return false;
            }
        });
        curatorFramework.start();

        DistributedLockProvider distributedLockProvider = new DistributedLockProvider(curatorFramework);
        Thread t = new Thread() {

            @Override
            public void run() {
                Lock lock = distributedLockProvider.getLock("/hello/lock");
                try {
                    lock.lock();
                    System.out.println("HELLO...................LOCK");
                    System.out.println("HELLO............... LOCK FINISH");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        t.start();

        Lock lock1 = distributedLockProvider.getLock("/hello/lock");
        try {
            System.out.println("GEEEEEEEEEEEEEEEEEEEEEEEE...T........LOCK");
            lock1.lock();
            System.out.println("HELLO.......OUT>>>>>>>>>>>>>>>>LOCK");
        } finally {
            lock1.unlock();
        }
    }

}