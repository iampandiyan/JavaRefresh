package Object;

public class CloneDemo {

    public static void main(String[] args) {
        Person original = new Person("Karuppasamy", new Address("Chennai"));
        Person shallow = original.shallowClone();
        System.out.println(original.getName()); // "Karuppasamy"
        System.out.println(shallow.getName()); // "Karuppasamy"
        System.out.println(original.getAddress().getCity()); // "Chennai"
        System.out.println(shallow.getAddress().getCity()); // "Chennai"
        shallow.address.city = "Bangalore";
        System.out.println(original.address.city); // "Bangalore" — original corrupted!
        System.out.println("After shallow cloning, changing the address in the cloned object also changes the original object's address because both objects share the same Address instance.");
        System.out.println(original.getAddress().getCity()); // "Bangalore"
        System.out.println(shallow.getAddress().getCity()); // "Bangalore"

        Person original2 = new Person("Pandiyan", new Address("Chennai"));
        Person deep = original2.deepClone();
        System.out.println(original2.getName()); // "Pandiyan"
        System.out.println(deep.getName()); // "Pandiyan"
        System.out.println(original2.getAddress().getCity()); // "Chennai"
        System.out.println(deep.getAddress().getCity()); // "Chennai"
        deep.address.city = "Mumbai";
        System.out.println(original2.address.city); // "Chennai" — original untouched
        System.out.println("After deep cloning, changing the address in the cloned object does not affect the original object's address because both objects have their own Address instances.");
        System.out.println(original2.getAddress().getCity()); // "Chennai"
        System.out.println(deep.getAddress().getCity()); // "Mumbai"
    }

}
