package src.com.bitongchong.part5.CyclicBarrierTest;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService excutor = Executors.newFixedThreadPool(4);
        CyclicBarrier barrier = new CyclicBarrier(4, new BossTask());
        excutor.execute(new MeetingTask(barrier, 3, "BOB"));
        excutor.execute(new MeetingTask(barrier, 4, "TOM"));
        excutor.execute(new MeetingTask(barrier, 5, "LILY"));
        excutor.execute(new MeetingTask(barrier, 6, "TANG"));

        excutor.execute(new EvaluateTask(barrier, 4, "BOB"));
        excutor.execute(new EvaluateTask(barrier, 5, "TOM"));
        excutor.execute(new EvaluateTask(barrier, 6, "LILY"));
        excutor.execute(new EvaluateTask(barrier, 6, "TANG"));

        excutor.shutdown();
    }
}
