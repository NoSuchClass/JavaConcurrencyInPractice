package com.bitongchong.second;

// 验证是否能够同时获取一个类的类锁和对象锁
public class ClassLockAndInstanceLock {
    static Object object = new Object();

    public static synchronized void func() throws InterruptedException {
        System.out.println(Thread.currentThread() + " get class lock start");
        Thread.sleep(2000);
        // object.wait()  : 会报错，为什么只能够在synchronized(){}这种的同步代码块中才能够使用wait()方法呢？
    }

    public static void func2() throws InterruptedException {
        synchronized (new ClassLockAndInstanceLock()) {
            System.out.println(Thread.currentThread() + " get instace lock and start....");
            Thread.sleep(2000);
        }
    }

    // 结论：可以
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                func();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                func2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                func();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
