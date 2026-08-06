package SESSION4_DESIGNPATTERN.Singleton;

public class SynchronizedSingleton {
    private static SynchronizedSingleton instance;

    private SynchronizedSingleton() {
        // private constructor
    }

    public static synchronized SynchronizedSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    public String getName() {
        return "SynchronizedSingleton";
    }
}
