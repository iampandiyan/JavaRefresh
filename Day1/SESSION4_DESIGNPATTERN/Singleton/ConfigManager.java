package SESSION4_DESIGNPATTERN.Singleton;

public class ConfigManager {
    private ConfigManager() {
        // Private constructor to prevent instantiation
    }
    private static class Holder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }
    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

}
enum ConfigManagerEnum {
    INSTANCE;
    public void displayConfig() {
        System.out.println("Displaying configuration settings.");
    }
}
