package com.weghst.pine.util.lock;

import org.testng.annotations.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-12-02 16:52
 */
@SuppressWarnings("ALL")
public class DistributedLockProviderTest {

    @Test
    public void testGetLock() throws Exception {
        DistributedLockProvider distributedLockProvider = new DistributedLockProvider("localhost:2181");
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