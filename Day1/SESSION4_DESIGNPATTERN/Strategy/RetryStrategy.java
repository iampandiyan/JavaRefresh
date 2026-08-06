package SESSION4_DESIGNPATTERN.Strategy;

public interface RetryStrategy {
    boolean shouldRetry(int attempt, Exception e);
}
