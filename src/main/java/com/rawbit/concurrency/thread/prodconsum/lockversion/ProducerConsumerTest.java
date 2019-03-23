package com.rawbit.concurrency.thread.prodconsum.lockversion;



public class ProducerConsumerTest {


    public static void main(String[] args) {

        WarehouseLockVersion warehouse = new WarehouseLockVersion(2000);

        Producer producer = new Producer(warehouse, 10);
        Consumer consumer = new Consumer(warehouse, 10);

        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(producer);
            thread.setName("PT-" + i);
            thread.start();
        }

        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(consumer);
            thread.setName("CT-" + i);
            thread.start();
        }
    }
}
