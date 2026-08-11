package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    static class DelayedTask implements Delayed {
        private final long runAtNanos;
        private final String name;

        DelayedTask(long delayMillis, String name) {
            this.runAtNanos = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(delayMillis);
            this.name = name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = runAtNanos - System.nanoTime();
            return unit.convert(diff, TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this) return 0;
            long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
            return Long.compare(diff, 0);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> dq = new DelayQueue<>();

        dq.put(new DelayedTask(1000, "T1-1s"));
        dq.put(new DelayedTask(300, "T2-0.3s"));
        dq.put(new DelayedTask(700, "T3-0.7s"));

        System.out.println("Polling from DelayQueue in scheduled order:");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            DelayedTask t = dq.take(); // blocks until the head's delay has expired
            System.out.printf("  took %s after %d ms\n", t, System.currentTimeMillis() - start);
        }
    }
}
/*
Polling from DelayQueue in scheduled order:
  took T2-0.3s after 305 ms
  took T3-0.7s after 709 ms
  took T1-1s after 1008 ms

*/