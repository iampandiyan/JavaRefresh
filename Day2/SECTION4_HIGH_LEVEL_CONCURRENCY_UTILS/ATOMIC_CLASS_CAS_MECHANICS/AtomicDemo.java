package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.ATOMIC_CLASS_CAS_MECHANICS;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private final AtomicInteger counter = new AtomicInteger(0);
    void increamentManually() { // manually reimplementing what incrementAndGet() does, to show the CAS loop
        int current, next;
        do {
            current = counter.get();
            next = current + 1;
        } while (!counter.compareAndSet(current, next));
    }
    public static void main(String[] args) {
        AtomicDemo demo = new AtomicDemo();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                demo.increamentManually();
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                demo.increamentManually();
            }
        }, "Thread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final counter value: " + demo.counter.get());
    }

}
