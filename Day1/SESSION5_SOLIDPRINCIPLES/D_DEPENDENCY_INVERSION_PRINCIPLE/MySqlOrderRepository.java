package SESSION5_SOLIDPRINCIPLES.D_DEPENDENCY_INVERSION_PRINCIPLE;

// VIOLATION: OrderService is directly coupled to a specific concrete repository.
// Swapping databases, or writing a unit test with a fake repository, means
// editing OrderService itself.
public class MySqlOrderRepository {
    void save(String order) {
        System.out.println("Saving order to MySQL database");
    }

}
