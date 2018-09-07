package com.rawbit.netty.ch2.aio;


import java.io.IOException;

public class TimeServer {

    public static void main(String[] args) throws IOException {

        int port = 8080;

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);

        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
