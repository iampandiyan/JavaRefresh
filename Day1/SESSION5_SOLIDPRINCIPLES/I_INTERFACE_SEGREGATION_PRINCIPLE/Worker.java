package SESSION5_SOLIDPRINCIPLES.I_INTERFACE_SEGREGATION_PRINCIPLE;
// VIOLATION: forces EVERY worker implementation to provide eat() and sleep(),
// even ones that make no sense for a robot.
public interface Worker {
    void code();
    void eat();
    void sleep();
}
