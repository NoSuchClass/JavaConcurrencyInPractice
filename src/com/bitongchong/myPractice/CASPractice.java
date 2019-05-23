package src.com.bitongchong.myPractice;

import java.util.concurrent.atomic.AtomicReference;

public class CASPractice {
    AtomicReference<Thread> reference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        CASPractice casLock = new CASPractice();
        new Thread(() -> {
            Thread.currentThread().setName("Thread A");
            casLock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            casLock.unlock();
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Thread B");
            casLock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            casLock.unlock();
        }).start();
    }

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " : coming!");
        while (!reference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " : come out!");
    }
}
