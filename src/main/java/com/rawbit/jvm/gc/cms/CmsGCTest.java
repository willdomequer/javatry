package com.rawbit.jvm.gc.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CmsGCTest {

    private static final List<Byte[]> bufferList = new ArrayList<>();



    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new PutJob());
            thread.setName("PT-" + i);
            thread.start();
        }

        Thread cleanThread = new Thread(new CleanJob());
        cleanThread.setName("CT");
        cleanThread.start();
    }


    static class PutJob implements Runnable {

        private static AtomicInteger PutCount = new AtomicInteger(0);

        @Override
        public void run() {

            while (true) {

                synchronized (bufferList) {

                    while (PutCount.get() > 5) {

                        PutCount.addAndGet(-1);

                        try {
                            bufferList.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    PutCount.incrementAndGet();

                    for (int i = 0; i < 100; i++) {
                        Byte[] buffer = new Byte[8*1024];
                        bufferList.add(buffer);
                    }
                    System.out.println("put finished");
                    bufferList.notifyAll();
                }
            }
        }
    }


    static class CleanJob implements Runnable {

        @Override
        public void run() {

            while (true) {

                synchronized (bufferList) {

                    while (bufferList.size() < 1024) {
                        try {
                            bufferList.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for (int i = 0; i < bufferList.size() / 4; i++) {
                        bufferList.remove(i);
                    }

                    System.out.println("clean finished");
                    bufferList.notifyAll();
                }
            }
        }
    }
}