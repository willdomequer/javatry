package com.rawbit.concurrency.threadlocaloom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalOOMTest {

    private static final int THREAD_LOOP_SIZE = 500;
    private static final int MOCK_DIB_DATA_LOOP_SIZE = 10000;

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<List<User>>();

    public static void main(String[] args) {

        ExecutorService exeService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);

        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            exeService.execute(()->{
                threadLocal.set(new ThreadLocalOOMTest().addBigList());
                Thread t = Thread.currentThread();
                System.out.println(Thread.currentThread().getName());
                //threadLocal.remove();
            });

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<User> addBigList() {
        List<User> params = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);

        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            params.add(new User("tom", "pwd" + i, "男", i));
        }
        return params;
    }

    class User {
        private String userName;
        private String password;
        private String sex;
        private int age;

        public User(String userName, String password, String sex, int age) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
            this.age = age;
        }
    }
}
