package com.letcode.learn.singleton;

//Initialization on Demand Holder
public class Singleton {

    private static class SingletonHolder {
        public final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}