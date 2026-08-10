package Day2.SECTION3_LOCKS.CONDITIONS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    private final Queue<Integer> buffer=new LinkedList<>();
    private final int capacity=5;
    private final Lock lock =new ReentrantLock();
    private final Condition notFull=lock.newCondition(); // producers wait here
    private final Condition notEmpty=lock.newCondition(); // consumers wait here

    void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                notFull.await(); // wait until there's space
            }
            buffer.add(value);
            System.out.println("Produced: " + value);
            notEmpty.signal(); // signal that there's at least one item
        } finally {
            lock.unlock();
        }
    }

    int consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                notEmpty.await(); // wait until there's an item
            }
            int value = buffer.poll();
            System.out.println("Consumed: " + value);
            notFull.signal(); // signal that there's space
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.produce(i);
                    Thread.sleep(100); // simulate production time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.consume();
                    Thread.sleep(150); // simulate consumption time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
