package SESSION3_COLLECTIONS.Set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetOrderingDemo {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>(List.of("Banana", "Apple", "Cherry","Mango", "Grapes"));
        Set<String> linkedHashSet = new LinkedHashSet<>(List.of("Banana", "Apple", "Cherry", "Mango", "Grapes"));
        Set<String> treeSet = new TreeSet<>(List.of("Banana", "Apple", "Cherry", "Mango", "Grapes"));
        
        System.out.println("HashSet: " + hashSet); // Unordered
        System.out.println("LinkedHashSet: " + linkedHashSet); // Insertion order   
        System.out.println("TreeSet: " + treeSet); // Sorted order

        TreeSet<Integer> scores=new TreeSet<>(List.of(50, 65, 70, 85, 9));
        System.out.println("Floor of 68: " + scores.floor(68)); // 65
        System.out.println("Ceiling of 68: " + scores.ceiling(68)); // 70
        /*
        HashSet: [Apple, Cherry, Grapes, Mango, Banana]
        LinkedHashSet: [Banana, Apple, Cherry, Mango, Grapes]
        TreeSet: [Apple, Banana, Cherry, Grapes, Mango]
        Floor of 68: 65
        Ceiling of 68: 70
         */

    }

}
