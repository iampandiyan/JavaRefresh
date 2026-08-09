package SESSION6_EXCEPTION_HANDLING;

public class TryWithResourcesDemo {
    static class NoisyResource implements AutoCloseable {
        public void use() { throw new RuntimeException("failure during use"); }
        public void close() { throw new RuntimeException("failure during close"); }
    }
    public static void main(String[] args) {
        try (NoisyResource r = new NoisyResource()) { r.use(); }
        catch (RuntimeException e) {
            System.out.println("Primary: " + e.getMessage());
            for (Throwable s : e.getSuppressed()) System.out.println("Suppressed: " + s.getMessage());
        }
    }
}
