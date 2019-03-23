package com.rawbit.concurrency.thread.prodconsum.lockversion;


public class Producer implements Runnable {

    private WarehouseLockVersion warehouse;
    private int num;


    public Producer(WarehouseLockVersion warehouse, int num) {
        this.warehouse = warehouse;
        this.num = num;
    }


    @Override
    public void run() {
        warehouse.produce(num);
    }
}
