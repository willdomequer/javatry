package com.rawbit.concurrency.thread.deadlock;


/**
 * 使用 jstack 查看线程死锁，示例如下：
 *
 *   C:\Users\willdomequer>jstack 19392
     2018-11-25 18:54:10
     Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.152-b16 mixed mode):

     "Thread 2" #13 prio=5 os_prio=0 tid=0x000000001f1d0000 nid=0x28b0 waiting for monitor entry [0x000000001fdef000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at com.rawbit.concurrency.thread.deadlock.DeadLockSample.run(DeadLockSample.java:19)
     - waiting to lock <0x000000076b598fa8> (a java.lang.String)
     - locked <0x000000076b598fe0> (a java.lang.String)

     "Thread 1" #12 prio=5 os_prio=0 tid=0x000000001f1af800 nid=0x4db4 waiting for monitor entry [0x000000001fcef000]
     java.lang.Thread.State: BLOCKED (on object monitor)
     at com.rawbit.concurrency.thread.deadlock.DeadLockSample.run(DeadLockSample.java:19)
     - waiting to lock <0x000000076b598fe0> (a java.lang.String)
     - locked <0x000000076b598fa8> (a java.lang.String)

     "Service Thread" #11 daemon prio=9 os_prio=0 tid=0x000000001f034000 nid=0x5088 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C1 CompilerThread3" #10 daemon prio=9 os_prio=2 tid=0x000000001eff7000 nid=0xd58 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001efe7000 nid=0x3dd0 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x000000001efe1000 nid=0x4df0 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x000000001ef84800 nid=0x4fb8 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x000000001ef82000 nid=0x24e8 runnable [0x000000001f5ee000]
     java.lang.Thread.State: RUNNABLE
     at java.net.SocketInputStream.socketRead0(Native Method)
     at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
     at java.net.SocketInputStream.read(SocketInputStream.java:171)
     at java.net.SocketInputStream.read(SocketInputStream.java:141)
     at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
     at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
     at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
     - locked <0x000000076b64f898> (a java.io.InputStreamReader)
     at java.io.InputStreamReader.read(InputStreamReader.java:184)
     at java.io.BufferedReader.fill(BufferedReader.java:161)
     at java.io.BufferedReader.readLine(BufferedReader.java:324)
     - locked <0x000000076b64f898> (a java.io.InputStreamReader)
     at java.io.BufferedReader.readLine(BufferedReader.java:389)
     at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)

     "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000000001ee2c000 nid=0x45a4 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000000001ee80800 nid=0x4ea0 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE

     "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x000000001ee11800 nid=0x4678 in Object.wait() [0x000000001f2ef000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x000000076b388ec8> (a java.lang.ref.ReferenceQueue$Lock)
     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
     - locked <0x000000076b388ec8> (a java.lang.ref.ReferenceQueue$Lock)
     at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
     at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

     "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x00000000034ca800 nid=0x4f94 in Object.wait() [0x000000001edee000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x000000076b386b68> (a java.lang.ref.Reference$Lock)
     at java.lang.Object.wait(Object.java:502)
     at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
     - locked <0x000000076b386b68> (a java.lang.ref.Reference$Lock)
     at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

     "main" #1 prio=5 os_prio=0 tid=0x00000000033d4800 nid=0x40f4 in Object.wait() [0x0000000002e7f000]
     java.lang.Thread.State: WAITING (on object monitor)
     at java.lang.Object.wait(Native Method)
     - waiting on <0x000000076b599018> (a com.rawbit.concurrency.thread.deadlock.DeadLockSample)
     at java.lang.Thread.join(Thread.java:1252)
     - locked <0x000000076b599018> (a com.rawbit.concurrency.thread.deadlock.DeadLockSample)
     at java.lang.Thread.join(Thread.java:1326)
     at com.rawbit.concurrency.thread.deadlock.DeadLockSample.main(DeadLockSample.java:37)

     "VM Thread" os_prio=2 tid=0x000000001cef9800 nid=0x4c30 runnable

     "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00000000033ea800 nid=0x43f0 runnable

     "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00000000033ec000 nid=0x40b4 runnable

     "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00000000033ed800 nid=0x5218 runnable

     "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x00000000033ef000 nid=0x4f88 runnable

     "GC task thread#4 (ParallelGC)" os_prio=0 tid=0x00000000033f2800 nid=0x10dc runnable

     "GC task thread#5 (ParallelGC)" os_prio=0 tid=0x00000000033f3800 nid=0x25fc runnable

     "GC task thread#6 (ParallelGC)" os_prio=0 tid=0x00000000033f6800 nid=0x5e0 runnable

     "GC task thread#7 (ParallelGC)" os_prio=0 tid=0x00000000033f8000 nid=0x24bc runnable

     "VM Periodic Task Thread" os_prio=2 tid=0x000000001f12b000 nid=0x30d8 waiting on condition

     JNI global references: 30


     Found one Java-level deadlock:
     =============================
     "Thread 2":
     waiting to lock monitor 0x000000001cf03638 (object 0x000000076b598fa8, a java.lang.String),
     which is held by "Thread 1"
     "Thread 1":
     waiting to lock monitor 0x000000001cf036e8 (object 0x000000076b598fe0, a java.lang.String),
     which is held by "Thread 2"

     Java stack information for the threads listed above:
     ===================================================
     "Thread 2":
     at com.rawbit.concurrency.thread.deadlock.DeadLockSample.run(DeadLockSample.java:19)
     - waiting to lock <0x000000076b598fa8> (a java.lang.String)
     - locked <0x000000076b598fe0> (a java.lang.String)
     "Thread 1":
     at com.rawbit.concurrency.thread.deadlock.DeadLockSample.run(DeadLockSample.java:19)
     - waiting to lock <0x000000076b598fe0> (a java.lang.String)
     - locked <0x000000076b598fa8> (a java.lang.String)

     Found 1 deadlock.
 *
 */
public class DeadLockSample extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";

        DeadLockSample t1 = new DeadLockSample("Thread 1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread 2", lockB, lockA);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
