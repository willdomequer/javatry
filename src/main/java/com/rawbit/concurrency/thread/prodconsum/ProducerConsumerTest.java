package com.rawbit.concurrency.thread.prodconsum;


public class ProducerConsumerTest {


    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse(2000);

        Producer producer = new Producer(warehouse, 100);
        Consumer consumer = new Consumer(warehouse, 100);

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(producer);
            thread.setName("PT-" + i);
            thread.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(consumer);
            thread.setName("CT-" + i);
            thread.start();
        }
    }
}
