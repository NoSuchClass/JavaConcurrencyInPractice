package src.com.bitongchong.part5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    static class MyBlockingQueue<T> {
        private final Semaphore semaphore;
        private final List<T> list;

        public MyBlockingQueue(int count) {
            this.semaphore = new Semaphore(count);
            this.list = Collections.synchronizedList(new ArrayList<T>());
        }

        public boolean add(T o) throws InterruptedException {
            semaphore.acquire();
            boolean wasAdded = list.add(o);
            if (!wasAdded) {
                semaphore.release();
            }
            return wasAdded;
        }

        public boolean remove(T o) {
            boolean wasRemoved = list.remove(o);
            if (wasRemoved) {
                semaphore.release();
            }
            return wasRemoved;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue queue = new MyBlockingQueue<Integer>(2);
        queue.add(99);
        queue.add(998);
    }
}
