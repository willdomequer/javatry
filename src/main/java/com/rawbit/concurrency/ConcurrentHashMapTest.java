package com.rawbit.concurrency;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    private static ConcurrentHashMap<Integer, String> container = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(new PutJob());
            thread.setName("T-" + i);
            thread.start();
        }
    }

    static class PutJob implements Runnable {
        @Override
        public void run() {
            for (int i = container.size(); i < container.size() + 100; i++) {
                container.put(i, Thread.currentThread().getName() + i);
            }
        }
    }

    static class GetJob implements Runnable {
        @Override
        public void run() {
            for (int i = container.size(); i < container.size() + 20; i++) {
                container.put(i, Thread.currentThread().getName() + i);
            }
        }
    }
}
