package Day2.SECTION3_LOCKS.ReentrantLockVsSynchronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final Lock lock = new ReentrantLock();
    private void doWithoutTimeout() {
        boolean acquired = false;
        try{
            acquired=lock.tryLock(1,TimeUnit.SECONDS);
            if(acquired){
                System.out.println("Lock acquired, performing operation");
                Thread.sleep(2000); // Simulate some work longer than the lock wait timeout
            } else {
                System.out.println("Could not acquire lock, operation skipped");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if(acquired){
                lock.unlock();
                System.out.println("Lock released"); // MUST unlock in finally — synchronized can't forget this, ReentrantLock can
            }
        }

    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();

        Thread thread1 = new Thread(() -> demo.doWithoutTimeout(), "Worker-1");
        Thread thread2 = new Thread(() -> demo.doWithoutTimeout(), "Worker-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
