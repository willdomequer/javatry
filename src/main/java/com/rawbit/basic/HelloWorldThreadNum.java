package com.rawbit.basic;

public class HelloWorldThreadNum {

    public static void main(String[] args) {

        System.out.println("Hello world!");

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = threadGroup;

        while (threadGroup != null) {
            topGroup = threadGroup;
            threadGroup = threadGroup.getParent();
        }

        int threadNum = topGroup.activeCount();
        Thread[] threads = new Thread[threadNum];

        topGroup.enumerate(threads);

        for (int i = 0; i < threadNum; i++) {
            System.out.println("thread-" + i + " : " + threads[i].getName());
        }
    }
}
