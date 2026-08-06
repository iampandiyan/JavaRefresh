package SESSION4_DESIGNPATTERN.Singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        demonstrateConfigManager();
        demonstrateEagerSingleton();
        demonstrateLazySingleton();
        demonstrateSynchronizedSingleton();
        demonstrateEnumSingleton();
    }

    private static void demonstrateConfigManager() {
        System.out.println("--- ConfigManager (Holder) ---");
        ConfigManager config1 = ConfigManager.getInstance();
        ConfigManager config2 = ConfigManager.getInstance();
        System.out.println("config1 == config2: " + (config1 == config2));
        System.out.println("config1 hashCode: " + config1.hashCode());
        System.out.println("config2 hashCode: " + config2.hashCode());
    }

    private static void demonstrateEagerSingleton() {
        System.out.println("--- EagerSingleton ---");
        EagerSingleton s1 = EagerSingleton.getInstance();
        EagerSingleton s2 = EagerSingleton.getInstance();
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("name: " + s1.getName());
    }

    private static void demonstrateLazySingleton() {
        System.out.println("--- LazySingleton ---");
        LazySingleton s1 = LazySingleton.getInstance();
        LazySingleton s2 = LazySingleton.getInstance();
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("name: " + s1.getName());
    }

    private static void demonstrateSynchronizedSingleton() {
        System.out.println("--- SynchronizedSingleton ---");
        SynchronizedSingleton s1 = SynchronizedSingleton.getInstance();
        SynchronizedSingleton s2 = SynchronizedSingleton.getInstance();
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("name: " + s1.getName());
    }

    private static void demonstrateEnumSingleton() {
        System.out.println("--- EnumSingleton ---");
        EnumSingleton s1 = EnumSingleton.INSTANCE;
        EnumSingleton s2 = EnumSingleton.INSTANCE;
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("name: " + s1.getName());
    }
}
