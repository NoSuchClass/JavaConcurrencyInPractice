package src.com.bitongchong.part5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest2 {
    public static long threadsTime(int nThreads) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        // 代表一个线程有两个需要等待的事件
        CountDownLatch endGate = new CountDownLatch(nThreads * 2);
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    // 一个事件耗时1s
                    startGate.await();
                    Thread.sleep(1000);
                    // 一个事件耗时2s
                    endGate.countDown();
                    Thread.sleep(2000);
                    endGate.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        long startTime = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(CountDownLatchTest2.threadsTime(10) / 1000000000);
        ExecutorService executorsService = Executors.newCachedThreadPool();
    }
}
