package SESSION3_COLLECTIONS.Iterators;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class FailFastDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("a", "b", "c","d"));
        try{
            for (String s : list) {
                System.out.println(s);
                if (s.equals("b")) {
                    list.remove(s); // This will cause ConcurrentModificationException
                }
            }
        } catch(ConcurrentModificationException  e){
            System.out.println("Caught CME — removing during for-each is unsafe");
        }

        Iterator<String> it=list.iterator();
        while(it.hasNext()){
            if (it.next().equals("b")) it.remove(); 
        }
        System.out.println(list); // [a, c, d]

         List<String> list2 = new ArrayList<>(List.of("a", "b", "c", "d"));
        list2.removeIf(s -> s.equals("c"));
        System.out.println(list2); // [a, b, d]

    }

}
