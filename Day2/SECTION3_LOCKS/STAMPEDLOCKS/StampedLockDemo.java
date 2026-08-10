package Day2.SECTION3_LOCKS.STAMPEDLOCKS;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    private double x, y;
    private final StampedLock lock = new StampedLock();
    void move(double deltaX, double deltaY) {
        long stamp = lock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
    double distanceFromOrigin() {
        long stamp = lock.tryOptimisticRead(); // no blocking, no actual lock acquired
        double currentX = x, currentY = y;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock(); // a write happened during our read — stamp is now invalid
            try { // fall back to a real (blocking) read lock
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public static void main(String[] args) {
        StampedLockDemo demo = new StampedLockDemo();

        Thread writer = new Thread(() -> {
            try {
                demo.move(3, 4);
                System.out.println("Writer moved to (3, 4)");
                Thread.sleep(100);
                demo.move(-1, 2);
                System.out.println("Writer moved to (2, 6)");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Writer");

        Thread reader = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    double distance = demo.distanceFromOrigin();
                    System.out.println("Reader distance: " + distance);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Reader");

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
/*
Writer moved to (3, 4)
Reader distance: 5.0
Reader distance: 5.0
Writer moved to (2, 6)
Reader distance: 6.324555320336759
Reader distance: 6.324555320336759
Reader distance: 6.324555320336759 */
}
