package src.com.bitongchong.part1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UnsafeSequence {
    // 多次执行后会出现两条线程调用时结果相同的情况
    private static int value = 0;

    public static void getNext() {
        value++;
        System.out.println(value);
    }

    // 安全
    public static synchronized void safeGetNext() {
        value++;
        System.out.println(value);
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10000);
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {

                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    getNext();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
