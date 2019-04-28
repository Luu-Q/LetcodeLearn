package com.letcode.learn.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class JvmTest {

    private static final int THREAD_COUNT = 10;

    private static final int FOR_COUNT = 1000;

    private static final int THREAD_ACTIVE_COUNT = 2;

    private volatile int[] a = {};


    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {

        final JvmTest test = new JvmTest();
        ExecutorService singleThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_COUNT; i++) {
            singleThreadPool.execute(() -> {
                for (int j = 0; j < FOR_COUNT; j++) {
                    test.increase();
                }
            });
        }
        singleThreadPool.shutdown();
        while (Thread.activeCount() > THREAD_ACTIVE_COUNT) {
            System.out.println("其他活跃的线程数" + Thread.activeCount());
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
