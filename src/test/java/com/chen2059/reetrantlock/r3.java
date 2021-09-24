package com.chen2059.reetrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC
 * @description:
 * @author: Chen2059
 * @create: 2021-09-15
 **/
@Slf4j(topic = "c.r3")
public class r3 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("启动");
            if (!lock.tryLock()) {
                log.debug("获取立刻失败 返回");
                return;
            }

            try {
                log.debug("获取锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();

        log.debug("获取锁");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
