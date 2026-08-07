package SESSION5_SOLIDPRINCIPLES.D_DEPENDENCY_INVERSION_PRINCIPLE;

public class DipDemo {
    public static void main(String[] args) {
        System.out.println("=== DIP Violation Example ===");
        OrderService viol = new OrderService();
        viol.placeOrder("order-123");

        System.out.println("\n=== DIP Compliant Example ===");
        // Inject different implementations without changing OrderServiceFixed
        OrderServiceFixed serviceWithMySql = new OrderServiceFixed(new MySqlOrderRepositoryFixed());
        serviceWithMySql.placeOrder("order-456");

        OrderServiceFixed serviceWithMemory = new OrderServiceFixed(new InMemoryOrderRepository());
        serviceWithMemory.placeOrder("order-789");
    }
}
