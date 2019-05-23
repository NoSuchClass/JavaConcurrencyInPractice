package src.com.bitongchong.part5.CyclicBarrierTest;

public class BossTask implements Runnable {

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("... ...会议进行，Boss发言... ...");
    }
}
