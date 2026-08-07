package SESSION5_SOLIDPRINCIPLES.I_INTERFACE_SEGREGATION_PRINCIPLE;

public class RobotWorker implements Worker {
    @Override
    public void code() {
        System.out.println("Robot is coding");
    }
    // forced, meaningless
    @Override
    public void eat() {
        // Not applicable for RobotWorker
        throw new UnsupportedOperationException("Robot does not eat");
    }

    @Override
    public void sleep() {
        // Not applicable for RobotWorker
        throw new UnsupportedOperationException("Robot does not sleep");
    }

}
