package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.THREAD_LOCAL;

public class ThreadLocalDemo {
    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();

    static void processRequest(String id) {
        correlationId.set(id);
        try {
            doWork();
        } finally {
            correlationId.remove(); // CRITICAL when running inside a pooled thread — see pitfall
        }
    }

    static void doWork() {
        System.out.println("Processing on thread " + Thread.currentThread().getName() + " with correlationId=" + correlationId.get());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> processRequest("REQ-1"));
        Thread t2 = new Thread(() -> processRequest("REQ-2"));
        t1.start(); t2.start();
        t1.join(); t2.join();
        // Each thread saw ONLY its own correlationId, despite sharing the same static ThreadLocal instance.
    }
}
/*
Processing on thread Thread-1 with correlationId=REQ-2
Processing on thread Thread-0 with correlationId=REQ-1
*/