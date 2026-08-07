package SESSION5_SOLIDPRINCIPLES.D_DEPENDENCY_INVERSION_PRINCIPLE;

public class OrderServiceFixed {
     private final OrderRepository repository; // depends on the abstraction only
    OrderServiceFixed(OrderRepository repository) 
    { this.repository = repository; } // injected, not constructed
    
    void placeOrder(String order) { repository.save(order); }

}
