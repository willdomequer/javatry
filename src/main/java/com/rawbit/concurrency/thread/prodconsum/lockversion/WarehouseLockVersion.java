package com.rawbit.concurrency.thread.prodconsum.lockversion;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseLockVersion {

    private static final LinkedList<String> STORAGE = new LinkedList<>();
    private int MAX_SIZE = 10000;

    private ReentrantLock LOCK = new ReentrantLock();

    public WarehouseLockVersion(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
    }


    public void produce(int num) {

        LOCK.lock();

        if (STORAGE.size() >= MAX_SIZE) {
            System.out.println(Thread.currentThread().getName() + "【暂停生产】最大产能： " + MAX_SIZE + "\t当前产能： " + STORAGE.size() + "\t准备生产： " + num);
            return;
        }

        for (int i = 0; i < num; i++) {
            STORAGE.add("prod-" + i + System.currentTimeMillis());
        }
        System.out.println(Thread.currentThread().getName() + " produced " + num + " products");

        LOCK.unlock();

    }


    public void consume(int num) {

        LOCK.lock();

        if (STORAGE.size() < num) {
            System.out.println(Thread.currentThread().getName() + "【暂停消费】当前可消费： " + STORAGE.size() + "\t需要消费： " + num);
            return;
        }

        for (int i = 0; i < num; i++) {
            System.out.println(Thread.currentThread().getName() + " consumed " + i);
            STORAGE.remove(0);
        }
        System.out.println(Thread.currentThread().getName() + " consumed " + num + " products");

        LOCK.unlock();
    }
}
