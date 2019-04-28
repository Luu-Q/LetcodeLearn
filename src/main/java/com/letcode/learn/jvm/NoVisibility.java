package com.letcode.learn.jvm;

import com.letcode.learn.thread.Bank;

public class NoVisibility {


    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bank.set();
                System.out.println(Thread.currentThread() + "--" + bank.get());
            }
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                bank.set();
                System.out.println(Thread.currentThread() + "--" + bank.get());
            }
        });


        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(Thread.currentThread());
        System.out.println(bank.get());

    }



}