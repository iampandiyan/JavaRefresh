package SECTION2_GENERICS.TypeErasureBoundedWildcards;

import java.util.List;

public class PecsDemo {
    static double sum(List<? extends Number> producer) {
        double total = 0;
        for (Number n : producer) {
            total += n.doubleValue();
        }
        return total;
    }

    static void fillWithOnes(List<? super Integer> consumer, int count) {
        for (int i = 0; i < count; i++) {
            consumer.add(1);
        }
    }
    public static void main(String[] args) {
        System.out.println(sum(List.of(1, 2, 3))); // List<Integer> is a producer of Number
        List<Number> bucket = new java.util.ArrayList<>();
        fillWithOnes(bucket, 5); // List<Number> is a consumer of Integer
        System.out.println(bucket); // Outputs: [1, 1, 1, 1, 1]
        List<String> strList = new java.util.ArrayList<>();
        List<Integer> intList = new java.util.ArrayList<>();
        System.out.println(strList.getClass() == intList.getClass()); // true, both are ArrayList at runtime
    }

}
