package SESSION4_DESIGNPATTERN.Strategy;

public class StrategyDemo {
    public static void main(String[] args) {
        System.out.println("--- ExponentialBackoffStrategy ---");
        simulateRetry(new ExponentialBackoffStrategy());

        System.out.println("\n--- ImmediateRetryStrategy ---");
        simulateRetry(new ImmediateRetryStrategy());
    }

    private static void simulateRetry(RetryStrategy strategy) {
        for (int attempt = 1; attempt <= 7; attempt++) {
            Exception error = new RuntimeException("Temporary failure");
            boolean shouldRetry = strategy.shouldRetry(attempt, error);
            System.out.println("Attempt " + attempt + ": shouldRetry=" + shouldRetry);
            if (!shouldRetry) {
                break;
            }
        }
    }
}
