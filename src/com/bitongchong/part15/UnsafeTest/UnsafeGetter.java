package src.com.bitongchong.part15.UnsafeTest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeGetter {

    private static Unsafe unsafe;

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        unsafe = (Unsafe) field.get(null);
        return unsafe;
    }
}