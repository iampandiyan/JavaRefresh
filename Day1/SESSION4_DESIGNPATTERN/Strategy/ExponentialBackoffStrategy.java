package SESSION4_DESIGNPATTERN.Strategy;

public class ExponentialBackoffStrategy implements RetryStrategy {
    
@Override    
public boolean shouldRetry(int attempt, Exception e) {
        // Implement exponential backoff logic here
        // For example, retry if the attempt is less than a certain threshold
        return attempt < 5 && !(e instanceof IllegalArgumentException); // Retry for the first 5 attempts
    }

}
