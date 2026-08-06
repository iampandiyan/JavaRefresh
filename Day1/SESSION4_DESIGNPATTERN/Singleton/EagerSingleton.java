package SESSION4_DESIGNPATTERN.Singleton;

public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        // private constructor
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return "EagerSingleton";
    }
}
