package com.rawbit.netty.ch2.aio;


public class TimeClient {

    public static void main(String[] args) throws Exception {

        new Thread(new AsyncTimeClientHandler("127.0.0.1", 8080), "AIO-AsycTimeClientHandler-001").start();
    }
}
