package com.letcode.learn.thread;

public class Bank {
    ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public Integer get() {
        return integerThreadLocal.get();
    }

    public void set() {
        integerThreadLocal.set(integerThreadLocal.get() + 10);
    }

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        System.out.println(thread.getName());
        System.out.println(thread.getId());
    }
}