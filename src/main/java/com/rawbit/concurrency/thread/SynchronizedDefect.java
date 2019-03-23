package com.rawbit.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class SynchronizedDefect {

    public synchronized void syncMethod() {

        try {
            TimeUnit.MINUTES.sleep(1);
            System.out.println(Thread.currentThread().getName() + " waked.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        SynchronizedDefect defect = new SynchronizedDefect();

        Thread t1 = new Thread(defect::syncMethod, "T1");
        t1.start();

        TimeUnit.MILLISECONDS.sleep(5);

        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
    }
}
