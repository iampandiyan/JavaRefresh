package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    private final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(2);

    void startProducer() {
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Producer: attempting to put " + i);
                    queue.put(i); // will block when capacity reached
                    System.out.println("Producer: put " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        producer.start();
    }

    void startConsumer() {
        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(700); // delay to allow producer to block
                while (true) {
                    Integer value = queue.take();
                    System.out.println("Consumer: took " + value);
                    Thread.sleep(500);
                    if (value == 5) break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        consumer.start();
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueueDemo demo = new LinkedBlockingQueueDemo();
        demo.startProducer();
        demo.startConsumer();

        Thread.sleep(4000);
    }
}
/*
Producer: attempting to put 1
Producer: put 1
Producer: attempting to put 2
Producer: put 2
Producer: attempting to put 3
Producer: put 3
Producer: attempting to put 4
Consumer: took 1
Consumer: took 2
Producer: put 4
Producer: attempting to put 5
Producer: put 5
Consumer: took 3
Consumer: took 4
Consumer: took 5
*/