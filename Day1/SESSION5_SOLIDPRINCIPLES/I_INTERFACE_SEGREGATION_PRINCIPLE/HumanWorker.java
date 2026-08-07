package SESSION5_SOLIDPRINCIPLES.I_INTERFACE_SEGREGATION_PRINCIPLE;

public class HumanWorker implements Codeable, Eatable, Sleepable {
    @Override
    public void code() {
        System.out.println("Human is coding");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Human is sleeping");
    }

}
