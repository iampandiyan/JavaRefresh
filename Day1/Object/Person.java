package Object;

public class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Person shallowClone() {
        try { return (Person) super.clone(); } catch (CloneNotSupportedException e) { throw new AssertionError(e); }}
    

    public Person deepClone() {
        Person copy = shallowClone();
        copy.address = this.address.clone();
        return copy;
    }
}
