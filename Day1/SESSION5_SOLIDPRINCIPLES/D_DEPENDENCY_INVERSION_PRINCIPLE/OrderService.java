package SESSION5_SOLIDPRINCIPLES.D_DEPENDENCY_INVERSION_PRINCIPLE;

public class OrderService {
 private final MySqlOrderRepository repository = new MySqlOrderRepository(); // hard-wired concrete class
    void placeOrder(String order) { repository.save(order); }
}
