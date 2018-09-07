package com.rawbit.netty.ch2.nio;

public class TimeClient {

    public static void main(String[] args) throws Exception {

        new Thread(new TimeClientHandle("127.0.0.1", 8080), "TimeClient-001").start();
    }
}
