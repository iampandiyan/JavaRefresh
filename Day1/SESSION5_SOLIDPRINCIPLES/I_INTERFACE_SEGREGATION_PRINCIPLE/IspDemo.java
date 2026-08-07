package SESSION5_SOLIDPRINCIPLES.I_INTERFACE_SEGREGATION_PRINCIPLE;

public class IspDemo {
    public static void main(String[] args) {
        System.out.println("=== ISP Violation Example ===");
        HumanWorker human = new HumanWorker();
        Worker robot = new RobotWorker();

        // Human works fine
        human.code();
        human.eat();
        human.sleep();

        // Robot is forced to implement eat/sleep; calling them throws
        robot.code();
        try {
            robot.eat();
        } catch (UnsupportedOperationException e) {
            System.out.println("Robot eat() error: " + e.getMessage());
        }
        try {
            robot.sleep();
        } catch (UnsupportedOperationException e) {
            System.out.println("Robot sleep() error: " + e.getMessage());
        }

        System.out.println("\n=== ISP Compliant Example ===");
        Codeable humanCoder = new HumanWorker();
        Codeable robotCoder = new RobotWorkerFixed();
        Eatable eater = new HumanWorker();
        Sleepable sleeper = new HumanWorker();

        // Only call the operations that make sense for each type
        humanCoder.code();
        robotCoder.code();
        eater.eat();
        sleeper.sleep();
    }
}
