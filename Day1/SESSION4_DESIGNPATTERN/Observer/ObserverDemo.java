package SESSION4_DESIGNPATTERN.Observer;

public class ObserverDemo {
    public static void main(String[] args) {
        OrderPublisher publisher = new OrderPublisher();

        publisher.subscribe(new EmailOrderObserver());
        publisher.subscribe(new SmsOrderObserver());

        publisher.updateStatus("ORD-1001", "PROCESSING");
        publisher.updateStatus("ORD-1001", "SHIPPED");
    }
}
