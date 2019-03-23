package com.rawbit.concurrency.threadpool;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 今天一个朋友问了我一个题目，A，B，C三个线程，按照顺序，依次循环输出A，B，C字符。
 * 思路我大概是知道的，但是从来没实践过，既然是别人提出技术问题，那么我肯定是在所不辞的。
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CycliBarrierSolver.solve();

        // SingleThreadExecutorSolver.solve();

    }

    /**
     * 思路 一：
     * 让多线程之间按照顺序输出，那么可以考虑用锁控制对应顺序，B等待A释放锁，C等待B释放锁，
     * 我们的选择可以有CountDownLatch和CyclicBarrier，但是有循环的条件，所以应该选择
     * 可以重置的CyclicBarrier。
     * 之后的思考就是如何通过CyclicBarrier实现C执行完后再执行A。
     */
    private static class CycliBarrierSolver {

        private static final CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
        private static final CyclicBarrier cyclicBarrier3 = new CyclicBarrier(2);
        private static final CyclicBarrier cyclicBarrier4 = new CyclicBarrier(3);

        static void solve() {

            Thread threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println("A");

                            // 控制A执行完在执行
                            cyclicBarrier2.await();

                            // 等待ABC都运行完
                            cyclicBarrier4.await();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadB = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            cyclicBarrier2.await();

                            System.out.println("B");

                            // 控制B执行完在执行C
                            cyclicBarrier3.await();

                            // 等待ABC都运行完
                            cyclicBarrier4.await();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread threadC = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            cyclicBarrier3.await();

                            System.out.println("c");

                            // 等待ABC都运行完
                            cyclicBarrier4.await();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            threadC.start();
            threadB.start();
            threadA.start();
        }
    }

    /**
     * 思路二
     * 使用SingleThreadExecutor能够保证线程顺序执行，其次每个线程执行完之后，
     * 再把自己放到线程池中去，那么就实现了循环。
     * 按照题目的意思来讲，这个思路其实是不对的，因为实际上执行Runnable的线程都是同一个工作线程。
     */
    private static class SingleThreadExecutorSolver {
        protected static final ExecutorService executorService =
                Executors.newSingleThreadExecutor();

        static void solve() {

            Runnable runnableA = new Runnable() {
                @Override
                public void run() {
                    System.out.println("A");
                    executorService.execute(this);
                }
            };

            Runnable runnableB = new Runnable() {
                @Override
                public void run() {
                    System.out.println("B");
                    executorService.execute(this);
                }
            };

            Runnable runnableC = new Runnable() {
                @Override
                public void run() {
                    System.out.println("C");
                    executorService.execute(this);
                }
            };

            executorService.execute(runnableA);
            executorService.execute(runnableB);
            executorService.execute(runnableC);
        }
    }
}
