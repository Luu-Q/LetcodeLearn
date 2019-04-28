package com.letcode.learn.queuetest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue(5);

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(5);

        ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                blockingQueue.offer("第【"+i+"】");
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                String poll = blockingQueue.poll();
                System.out.println(poll);
            }
        }).start();




    }
}
