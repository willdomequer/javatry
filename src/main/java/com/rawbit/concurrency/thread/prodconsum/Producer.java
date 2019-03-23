package com.rawbit.concurrency.thread.prodconsum;


public class Producer implements Runnable {

    private Warehouse warehouse;
    private int num;


    public Producer(Warehouse warehouse, int num) {
        this.warehouse = warehouse;
        this.num = num;
    }


    @Override
    public void run() {
        while (true) {
            warehouse.produce(num);
        }
    }
}
