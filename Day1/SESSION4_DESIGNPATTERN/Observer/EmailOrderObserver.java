package SESSION4_DESIGNPATTERN.Observer;

public class EmailOrderObserver implements OrderObserver {
    @Override
    public void onOrderStatusChanged(String orderId, String newStatus) {
        System.out.println("Email observer: Order " + orderId + " changed to " + newStatus);
    }
}
