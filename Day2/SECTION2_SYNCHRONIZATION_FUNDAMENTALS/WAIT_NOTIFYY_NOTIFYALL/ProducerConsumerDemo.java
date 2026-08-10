package Day2.SECTION2_SYNCHRONIZATION_FUNDAMENTALS.WAIT_NOTIFYY_NOTIFYALL;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 5;

    synchronized void produce(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait(); // Wait until there's space in the buffer
        }
        buffer.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); // Notify consumers that an item has been produced
    }

    synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // Wait until there's an item to consume
        }
        int value = buffer.poll();
        System.out.println("Consumed: " + value);
        notifyAll(); // Notify producers that an item has been consumed
        return value;
    }

    public static void main(String[] args) {
        ProducerConsumerDemo demo = new ProducerConsumerDemo();

        // Producer thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.produce(i);
                    Thread.sleep(100); // Simulate time taken to produce
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.consume();
                    Thread.sleep(150); // Simulate time taken to consume
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

}
