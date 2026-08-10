package Day2.SECTION2_SYNCHRONIZATION_FUNDAMENTALS.WAIT_NOTIFYY_NOTIFYALL;

import java.util.LinkedList;
import java.util.Queue;

public class RealtimeWaitNotifyDemo {
    private final Queue<String> queue = new LinkedList<>();
    private final int capacity = 3;
    private final Object lock = new Object();

    void sendMessage(String message) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacity) {
                System.out.println("[Producer] waiting, queue full (" + queue.size() + "/" + capacity + ")");
                lock.wait();
            }

            queue.add(message);
            System.out.println("[Producer] sent: " + message + " (queue size: " + queue.size() + ")");
            lock.notify(); // Wake one waiting consumer
        }
    }

    String receiveMessage() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                System.out.println("[" + Thread.currentThread().getName() + "] waiting, queue empty");
                lock.wait();
            }

            String message = queue.poll();
            System.out.println("[" + Thread.currentThread().getName() + "] received: " + message + " (queue size: " + queue.size() + ")");
            lock.notifyAll(); // Wake all waiting threads so they can re-check state
            return message;
        }
    }

    public static void main(String[] args) {
        RealtimeWaitNotifyDemo demo = new RealtimeWaitNotifyDemo();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 8; i++) {
                    demo.sendMessage("msg-" + i);
                    Thread.sleep(120);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 4; i++) {
                    demo.receiveMessage();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-1");

        Thread consumer2 = new Thread(() -> {
            try {
                for (int i = 1; i <= 4; i++) {
                    demo.receiveMessage();
                    Thread.sleep(180);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
