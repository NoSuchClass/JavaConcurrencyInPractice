package src.com.bitongchong.part6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 40; i++) {
            System.out.println(i);
            executorService.execute(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread() + "fuck");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        executorService.execute(() -> System.out.println(2323));
    }
}
