package com.bitongchong.second;

public class LazyInitRace {
    /*
    相当于是一个线程不安全的懒汉式单例模式，如果想将其改造为线程安全，直接参考单例模式进行修改即可

    懒汉式（线程安全）：
    private static Object object = null;
    public static synchornized Object getInstance(){
        if (object == null) object = new Object();
        return object;
    }

    饿汉式（线程安全）：
    private static Object object = new Object();
    public static Object getInstance(){
        return object;
    }

    双重锁校验（线程安全）：
    private static volatile Object object = null;
    public static Object getInstance(){
        if(object == null){
            synchronized (LazyInitRace.class){
                if(object == null){
                    object = new Object();
                }
            }
        }
        return object;
    }

    静态内部类实现：
    private static class SingletonHolder{
        private static final  Object OBJECT = new Object();
    }

    public static Object getInstance(){
        return SingletonHolder.OBJECT;
    }
    */
    private static Object object = null;

    public static Object getInstance() {
        if (object == null) object = new Object();
        return object;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(LazyInitRace.getInstance().toString());
                }
            }).start();
        }

    }
}
