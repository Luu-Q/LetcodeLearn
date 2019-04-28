package com.letcode.learn.thread;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {

        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 200;
        TimeUnit unit = TimeUnit.MICROSECONDS;
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //队列策略
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue();

        BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(5);

        BlockingQueue<Runnable> synchronousQueue = new SynchronousQueue();
        //拒绝策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        //缓存线程池，核心线程0，最大线程无限，队列是SynchronousQueue，任务进来队列，然后，一个一个执行
        ExecutorService executor = Executors.newCachedThreadPool();
        //单线程池，核心和最大为0，队列是LinkedBlockingQueue，第一个任务进队列，剩下的全进队列，然后依次执行
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //定容线程池，核心和最大一样，需自定义个数，队列是LinkedBlockingQueue，核心满后进队列，然后从队列取依次执行
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);


        ThreadPoolExecutor executor1 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, synchronousQueue, threadFactory, handler);

        try {
            for (int i = 0; i < 20; i++) {
                executor.execute(new MyTask(i));


//                System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                        executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
            }

            executor.shutdown();
        } catch (Exception e) {
            executor.shutdown();
            e.printStackTrace();
        }


        System.out.println(executor.isShutdown());


    }
}

class MyTask implements Runnable {

    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行【task-" + taskNum + "】start");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完毕【task-" + taskNum + "】end");
    }
}
