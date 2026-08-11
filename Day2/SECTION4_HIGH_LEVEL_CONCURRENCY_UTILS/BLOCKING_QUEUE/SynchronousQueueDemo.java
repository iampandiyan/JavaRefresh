package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> sq = new SynchronousQueue<>();

        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producer: ready to put item-1");
                sq.put("item-1"); // will block until consumer takes
                System.out.println("Producer: put item-1 and returned");

                System.out.println("Producer: ready to put item-2");
                boolean offered = sq.offer("item-2"); // non-blocking attempt
                System.out.println("Producer: offer item-2 result=" + offered);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(500); // wait so producer blocks on put
                System.out.println("Consumer: attempting to take");
                String v = sq.take();
                System.out.println("Consumer: took " + v);

                Thread.sleep(300);
                System.out.println("Consumer: attempting to take again (there may be none)");
                String v2 = sq.poll();
                System.out.println("Consumer: poll returned " + v2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
/*
Producer: ready to put item-1
Consumer: attempting to take
Producer: put item-1 and returned
Producer: ready to put item-2
Consumer: took item-1
Producer: offer item-2 result=false
Consumer: attempting to take again (there may be none)
Consumer: poll returned null
*/