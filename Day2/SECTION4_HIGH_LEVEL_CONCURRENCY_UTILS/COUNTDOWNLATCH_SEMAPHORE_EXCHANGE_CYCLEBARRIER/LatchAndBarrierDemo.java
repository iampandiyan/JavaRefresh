package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.COUNTDOWNLATCH_SEMAPHORE_EXCHANGE_CYCLEBARRIER;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class LatchAndBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
         // CountDownLatch: main thread waits for 3 worker threads to finish initialization, ONCE.
         CountDownLatch initLatch = new CountDownLatch(3);
         for(int i=1;i<=3;i++){
            int id=1;
            new Thread(()->{
                System.out.println("Worker"+id+" initializing...");
                initLatch.countDown(); // signal that this worker has finished initialization
            }).start();
         }

         initLatch.await(); // blocks main thread until all 3 have called countDown()
         System.out.println("All Workers Initialized - main Proceeds");

         // CyclicBarrier: 3 worker threads must all reach a checkpoint before ANY proceeds — reusable across rounds.
         CyclicBarrier barrier=new CyclicBarrier(3,()-> {
            System.out.println("All 3 reached the barrier — proceeding together");
         });
         for(int i=0;i<3;i++){
            int id=1;
             new Thread(() -> {
                try {
                    System.out.println("Worker " + id + " reached checkpoint");
                    barrier.await(); // blocks until all 3 threads call await()
                    System.out.println("Worker " + id + " proceeding past barrier");
                } catch (Exception e) { Thread.currentThread().interrupt(); }
            }).start();
         }
    }

}
/*
Worker1 initializing...
Worker1 initializing...
Worker1 initializing...
All Workers Initialized - main Proceeds
Worker 1 reached checkpoint
Worker 1 reached checkpoint
Worker 1 reached checkpoint
All 3 reached the barrier ? proceeding together
Worker 1 proceeding past barrier
Worker 1 proceeding past barrier
Worker 1 proceeding past barrier
*/
