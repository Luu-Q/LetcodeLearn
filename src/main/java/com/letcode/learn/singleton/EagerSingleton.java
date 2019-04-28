package com.letcode.learn.singleton;

/**
 * 饿汉模式
 */
public class EagerSingleton {


    private final static EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getInstanceSingleton(){
        return eagerSingleton;
    }
}
