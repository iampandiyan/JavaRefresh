package Day2.SECTION1_THREAD;

public class RealTimeThreadDemo {
    public static void main(String[] args) {
        Runnable clockTask = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Clock tick " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Clock thread interrupted");
                    return;
                }
            }
            System.out.println("Clock thread finished.");
        };

        Thread clockThread = new Thread(clockTask, "ClockThread");
        clockThread.start();

        System.out.println("Main thread continues working while clock runs...");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Main task step " + i);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread interrupted");
                return;
            }
        }

        try {
            clockThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread finished after waiting for the clock.");
    }
}
