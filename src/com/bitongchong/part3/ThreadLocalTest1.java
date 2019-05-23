package com.bitongchong.third;

public class ThreadLocalTest1 {
    // 验证ThreadLocal被同一个线程调用的时候能够返回相同的实例对象，而其他线程进行调用的时候
    // 会返回不同的实例对象，每一个线程操作的对象都是独立的，因此可以实现线程封闭技术
    private static ThreadLocal<Object> objectThreadLocal = new ThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            return new Object();
        }
    };

    public static Object getObject() {
        return objectThreadLocal.get();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread() + getObject().toString());
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread() + getObject().toString());
        }).start();
        System.out.println(Thread.currentThread() + getObject().toString());
        System.out.println(Thread.currentThread() + getObject().toString());
    }
}
