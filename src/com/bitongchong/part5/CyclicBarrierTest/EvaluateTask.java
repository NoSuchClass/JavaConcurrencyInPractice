package src.com.bitongchong.part5.CyclicBarrierTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class EvaluateTask implements Runnable {
    private CyclicBarrier barrier;
    private int time;
    private String name;

    public EvaluateTask(CyclicBarrier barrier, int time, String name) {
        this.barrier = barrier;
        this.time = time;
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(time * 1000);
            System.out.println("Boss对" + name + "的发言进行总结... ...");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}


