package SESSION4_DESIGNPATTERN.Singleton;

public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        // private constructor
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public String getName() {
        return "LazySingleton";
    }
}
