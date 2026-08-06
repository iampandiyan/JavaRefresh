package SESSION4_DESIGNPATTERN.Observer;

import java.util.ArrayList;
import java.util.List;

public class OrderPublisher {
    private final List<OrderObserver> observers = new ArrayList<>();
    void subscribe(OrderObserver o) {
        observers.add(o);
    }
    void updateStatus(String id, String status) { 
       observers.forEach(o -> o.onOrderStatusChanged(id, status));
    }
}
