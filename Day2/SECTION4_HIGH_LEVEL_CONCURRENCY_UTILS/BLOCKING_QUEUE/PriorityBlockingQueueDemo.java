package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {
    static class Task implements Comparable<Task> {
        final int priority;
        final String name;

        Task(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(this.priority, o.priority);
        }

        @Override
        public String toString() {
            return name + "(p=" + priority + ")";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> pq = new PriorityBlockingQueue<>();

        pq.put(new Task(5, "Low"));
        pq.put(new Task(1, "High"));
        pq.put(new Task(3, "Medium"));

        System.out.println("Polling tasks in priority order:");
        while (!pq.isEmpty()) {
            Task t = pq.take();
            System.out.println("  " + t);
            Thread.sleep(300);
        }
    }
}
/*
Polling tasks in priority order:
  High(p=1)
  Medium(p=3)
  Low(p=5)
*/
