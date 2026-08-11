package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.COUNTDOWNLATCH_SEMAPHORE_EXCHANGE_CYCLEBARRIER;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    private final Exchanger<String> exchanger = new Exchanger<>();

    void producer() {
        Thread t = new Thread(() -> {
            try {
                String[] items = {"A-1", "A-2", "A-3"};
                for (String item : items) {
                    System.out.println("Producer prepared: " + item);
                    String received = exchanger.exchange(item);
                    System.out.println("Producer received: " + received);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        t.start();
    }

    void consumer() {
        Thread t = new Thread(() -> {
            try {
                String[] items = {"B-1", "B-2", "B-3"};
                for (String item : items) {
                    System.out.println("Consumer prepared: " + item);
                    String received = exchanger.exchange(item);
                    System.out.println("Consumer received: " + received);
                    Thread.sleep(350);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {
        ExchangerDemo demo = new ExchangerDemo();
        demo.producer();
        demo.consumer();

        // Let demo run
        Thread.sleep(3000);
    }
}
/*
Consumer prepared: B-1
Producer prepared: A-1
Consumer received: A-1
Producer received: B-1
Producer prepared: A-2
Consumer prepared: B-2
Producer received: B-2
Consumer received: A-2
Producer prepared: A-3
Consumer prepared: B-3
Consumer received: A-3
Producer received: B-3
 */
