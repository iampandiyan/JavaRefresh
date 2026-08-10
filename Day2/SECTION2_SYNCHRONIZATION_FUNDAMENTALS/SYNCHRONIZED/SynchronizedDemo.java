package Day2.SECTION2_SYNCHRONIZATION_FUNDAMENTALS.SYNCHRONIZED;

public class SynchronizedDemo {
    private int counter = 0;

    synchronized void increment() {
        counter++;
    }

    synchronized void incrementTwice() {
        increment();
        increment();
    }
    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
       

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                demo.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        System.out.println("Expected 1000, got: " + demo.counter); // reliably 200000 WITH synchronize    
        try {
            thread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Expected 1000, got: " + demo.counter); // reliably 200000 WITH synchronize    
       

    }

}
