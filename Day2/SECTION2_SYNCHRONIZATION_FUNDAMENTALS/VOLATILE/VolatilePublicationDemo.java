package Day2.SECTION2_SYNCHRONIZATION_FUNDAMENTALS.VOLATILE;

public class VolatilePublicationDemo {
    static class Config{
        int timeout;
        int endpoint;
    }
    private static volatile Config config; //volatile on the REFERENCE, not the fields inside Config

    static void publish(){
        Config c = new Config();
        c.timeout = 1000;
        c.endpoint = 8080;
        config = c; //volatile write — happens-before guarantees timeout & endpoint are ALSO visible
    }
    static void consume(){
        Config c = config; //volatile read
        if(c != null){
            System.out.println("Timeout: " + c.timeout + ", Endpoint: " + c.endpoint);
        } else {
            System.out.println("Config not initialized yet.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Publishing config...");
        publish();
        System.out.println("Consuming config...");
        consume();
    }
}
