package SESSION4_DESIGNPATTERN.Strategy;

public class ImmediateRetryStrategy implements RetryStrategy {
    @Override
    public boolean shouldRetry(int attempt, Exception e) {
        return attempt < 3; // retry immediately up to 2 times
    }
}
