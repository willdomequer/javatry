package com.rawbit.concurrency.thread.prodconsum;


public class Consumer implements Runnable {

    private Warehouse warehouse;
    private int num;


    public Consumer(Warehouse warehouse, int num) {
        this.warehouse = warehouse;
        this.num = num;
    }


    @Override
    public void run() {
        while (true) {
            warehouse.consume(num);
        }
    }
}
