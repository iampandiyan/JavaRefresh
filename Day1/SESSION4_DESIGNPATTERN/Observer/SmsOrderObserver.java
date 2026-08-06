package SESSION4_DESIGNPATTERN.Observer;

public class SmsOrderObserver implements OrderObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String newStatus) {
        System.out.println("SMS observer: Order " + orderId + " changed to " + newStatus);
    }
}
