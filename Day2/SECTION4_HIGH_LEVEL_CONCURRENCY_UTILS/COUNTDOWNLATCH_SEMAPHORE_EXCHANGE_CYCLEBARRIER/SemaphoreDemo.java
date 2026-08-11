package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.COUNTDOWNLATCH_SEMAPHORE_EXCHANGE_CYCLEBARRIER;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private final Semaphore connectionPool=new Semaphore(2);

    void useConnection(int workerId) throws InterruptedException{
        connectionPool.acquire();
        try {
            System.out.println("Worker " + workerId + " using connection");
            Thread.sleep(500);
        } finally {
            connectionPool.release(); // MUST release, same discipline as locks
        }
    }

    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();

        Runnable workerTask = () -> {
            int workerId = Integer.parseInt(Thread.currentThread().getName().replace("Worker-", ""));
            try {
                demo.useConnection(workerId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread worker1 = new Thread(workerTask, "Worker-1");
        Thread worker2 = new Thread(workerTask, "Worker-2");
        Thread worker3 = new Thread(workerTask, "Worker-3");
        Thread worker4 = new Thread(workerTask, "Worker-4");

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

        try {
            worker1.join();
            worker2.join();
            worker3.join();
            worker4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
/*
Worker 1 using connection
Worker 2 using connection
Worker 3 using connection
Worker 4 using connection
*/