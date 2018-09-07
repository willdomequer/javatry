package com.rawbit.concurrency.thread.prodcon.lockversion;


public class Consumer implements Runnable {

    private WarehouseLockVersion warehouse;
    private int num;


    public Consumer(WarehouseLockVersion warehouse, int num) {
        this.warehouse = warehouse;
        this.num = num;
    }


    @Override
    public void run() {
        warehouse.consume(num);
    }
}
