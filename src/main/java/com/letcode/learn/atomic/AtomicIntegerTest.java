package com.letcode.learn.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            threadPool.execute(new IncTask());
        }

        threadPool.shutdown();



    }
}

class IncTask implements Runnable {

    AtomicInteger inc = new AtomicInteger();

//    public static int inc = 0;

    public void increase() {
//        inc++;
        inc.incrementAndGet();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            increase();
        }
        System.out.println(inc.get());
    }
}
