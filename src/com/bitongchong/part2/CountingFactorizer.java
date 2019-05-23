package com.bitongchong.second;

import java.util.concurrent.atomic.AtomicInteger;

public class CountingFactorizer {
    private static final AtomicInteger count = new AtomicInteger(1);
    private static int count2 = 1;

    public static int getCount() {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> CountingFactorizer.addCountSafe()).start();
        }
        return count.get();
    }

    public static void addCountSafe() {
        count.incrementAndGet();
    }

    public static void addCountUnsafe() {
        count2++;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getCount());
    }
}
