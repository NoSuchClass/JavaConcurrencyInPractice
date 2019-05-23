package com.bitongchong.five;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest1 {
    private static int n = 0;

    public long timeTask(int nThreads, Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                endGate.countDown();
                task.run();
                System.out.println(Thread.currentThread() + " start");
            }).start();
        }
        startGate.countDown();
        long startTime = System.nanoTime();
        endGate.await();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new com.bitongchong.five.CountDownLatchTest1().timeTask(100, () -> {
            System.out.println(n++);
        }));
    }
}
