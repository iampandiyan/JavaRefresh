package SESSION5_SOLIDPRINCIPLES.D_DEPENDENCY_INVERSION_PRINCIPLE;

public class InMemoryOrderRepository implements OrderRepository {
    @Override
    public void save(String order) {
        System.out.println("Saving order to in-memory database");
    }

}
