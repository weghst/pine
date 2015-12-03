package com.weghst.pine.util.lock;

import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.concurrent.locks.Lock;

import static org.testng.Assert.*;

/**
 * @author Zou Yong(zouyong@mychebao.com)
 * @date 2015-12-02 14:24
 */
@SuppressWarnings("ALL")
public class LocalLockProviderTest {

    @Test
    public void testGetLock() throws Exception {
        LocalLockProvider localLockProvider = new LocalLockProvider();

        new Thread() {

            @Override
            public void run() {
                Lock lock = localLockProvider.getLock("/hello/lock");
                try {
                    lock.lock();
                    System.out.println("HELLO...................LOCK");
                    Thread.sleep(1000);
                    System.out.println("HELLO............... LOCK FINISH");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();

        Thread.sleep(200);
        Lock lock1 = localLockProvider.getLock("/hello/lock");
        try {
            System.out.println("GEEEEEEEEEEEEEEEEEEEEEEEE...T........LOCK");
            lock1.lock();
            Thread.sleep(1000 * 20);
            System.out.println("HELLO.......OUT>>>>>>>>>>>>>>>>LOCK");
        } finally {
            lock1.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        LocalLockProvider localLockProvider = new LocalLockProvider();

        new Thread() {

            @Override
            public void run() {
                Lock lock = localLockProvider.getLock("/hello/lock");
                try {
                    lock.lock();
                    System.out.println("HELLO...................LOCK");
                    Thread.sleep(1000);
                    System.out.println("HELLO............... LOCK FINISH");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }.start();

        Thread.sleep(200);
        Lock lock = localLockProvider.getLock("/hello/lock");
        try {
            System.out.println("GEEEEEEEEEEEEEEEEEEEEEEEE...T........LOCK");
            lock.lock();
            System.out.println("HELLO.......OUT>>>>>>>>>>>>>>>>LOCK");
        } finally {
            lock.unlock();
        }
    }
}