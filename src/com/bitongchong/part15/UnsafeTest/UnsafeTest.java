package src.com.bitongchong.part15.UnsafeTest;

import sun.misc.Unsafe;

/**
 * 模拟AtomicInteger编写了一个CAS操作
 */
public class UnsafeTest {

    private static long valueOffset;

    private volatile long value;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        UnsafeTest unsafeTest = new UnsafeTest();
        for (int i = 0; i < 100; i++) {
            unsafeTest.addAndGet(1);
        }
        System.out.println(unsafeTest.value);
    }

    public long addAndGet(int val) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeGetter.getUnsafe();
        int nowValue;
        try {
            valueOffset = (int) unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        do {
            nowValue = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapLong(this, valueOffset, nowValue, nowValue + val));
        return nowValue + val;
    }
}
