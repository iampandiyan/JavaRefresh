package SESSION3_COLLECTIONS.Compare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

record Employee(String name, int age, double salary) {}

public class ComparableComparatorDemo {
    public static void main(String[] args) {
        List<Employee> employees =new ArrayList<>(List.of(
            new Employee("Karuppasamy", 35, 95000),
            new Employee("Arun", 28, 95000),
            new Employee("Divya", 28, 87000)
        ));
        employees.sort(Comparator.comparingInt(Employee::age).thenComparing(Comparator.comparingDouble(Employee::salary).reversed().thenComparing(Employee::name)));
        employees.forEach(e -> System.out.println(e.name() + " - Age: " + e.age() + ", Salary: " + e.salary()));
        /*
         * Output:
         * Arun - Age: 28, Salary: 95000.0
            Divya - Age: 28, Salary: 87000.0
            Karuppasamy - Age: 35, Salary: 95000.0
         */
    }

}
