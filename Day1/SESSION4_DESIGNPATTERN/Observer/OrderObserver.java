package SESSION4_DESIGNPATTERN.Observer;

public interface OrderObserver {
    void onOrderStatusChanged(String orderId, String newStatus);
}
