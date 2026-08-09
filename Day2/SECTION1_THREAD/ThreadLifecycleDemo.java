package Day2.SECTION1_THREAD;

public class ThreadLifecycleDemo {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread worker=new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Worker thread is waiting...");
                    lock.wait(); // Worker thread waits here
                    System.out.println("Worker thread resumed.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        System.out.println("Worker thread state before start: " + worker.getState());// NEW
        worker.start(); 
        System.out.println("Worker thread state after start: " + worker.getState());// RUNNABLE
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

         System.out.println("After start, inside wait(): " + worker.getState()); // WAITING
         synchronized (lock) {
            lock.notify(); // Notify the worker thread to resume
         }
         try {
             Thread.sleep(100);
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }
           System.out.println("After notify: " + worker.getState());// TERMINATED
    }
}
