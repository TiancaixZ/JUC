package com.chen2059.reetrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC
 * @description:
 * @author: Chen2059
 * @create: 2021-09-14
 **/
@Slf4j(topic = "c.r1")
public class r1 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            try {
                log.debug("尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("没有获取到锁");
                e.printStackTrace();
            }

            try {
                log.debug("获取到锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("打断t1");
        t1.interrupt();
    }
}
