package com.rawbit.concurrency.lock.boolock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {

    private Thread currentThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();


    @Override
    public void lock() throws InterruptedException {

        synchronized (this) {
            while (locked) {
                blockedList.add(currentThread);
                this.wait();
            }

            blockedList.remove(currentThread);
            this.locked = true;
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {

    }

    @Override
    public void unlock() {

    }

    @Override
    public List<Thread> getBlockedThreads() {
        return null;
    }
}
