package src.com.bitongchong.part5.CyclicBarrierTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MeetingTask implements Runnable {
    private CyclicBarrier barrier;
    private int time;
    private String name;

    public MeetingTask(CyclicBarrier barrier, int time, String name) {
        this.barrier = barrier;
        this.time = time;
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(time * 1000);
            System.out.println("员工" + name + "到达会议室... ...");
            barrier.await();
            System.out.println("员工" + name + "进行汇报... ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
