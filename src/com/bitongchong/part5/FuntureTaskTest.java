package com.bitongchong.five;

import java.io.FileReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FuntureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        FutureTask<Integer> task = new FutureTask<>(()-> {
            for (int i = 0; i < 1000000; i++) {
                System.out.println("i am still alive");
            }
            return 888;
        });

        Thread thread = new Thread(task);

        thread.start();
        task.get();
        System.out.println("main thread finish");*/

        FutureTask<String> task = new FutureTask<>(() -> {
            StringBuffer sb = new StringBuffer();
            char[] buff = new char[100];
            FileReader reader = new FileReader("C:\\Users\\HP\\Desktop\\test1.txt");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            while (reader.read(buff) != -1) {
                sb.append(buff);
            }
            return sb.toString();
        });

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("main am working");
        }
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(task.get());
    }
}
