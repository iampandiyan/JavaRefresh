package SESSION3_COLLECTIONS.List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformanceDemo {
    public static void main(String[] args) {
        int n=50_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList=new LinkedList<>();
        long t1=System.nanoTime();
        for(int i=0;i<n;i++){
            arrayList.add(0,i);
        }
        long arrayListHeadInsertTime = System.nanoTime() - t1;

        long t2 = System.nanoTime();
        for (int i = 0; i < n; i++) linkedList.add(0, i);
        long linkedListHeadInsertTime = System.nanoTime() - t2;

        System.out.println("ArrayList head-insert (ms): " + arrayListHeadInsertTime / 1_000_000);
        System.out.println("LinkedList head-insert (ms): " + linkedListHeadInsertTime / 1_000_000);

        long t3 = System.nanoTime();
        for (int i = 0; i < 10_000; i++) arrayList.get(arrayList.size() / 2);
        long arrayListGetTime = System.nanoTime() - t3;

        long t4 = System.nanoTime();
        for (int i = 0; i < 10_000; i++) linkedList.get(linkedList.size() / 2);
        long linkedListGetTime = System.nanoTime() - t4;

        System.out.println("ArrayList get (ms): " + arrayListGetTime / 1_000_000);
        System.out.println("LinkedList get (ms): " + linkedListGetTime / 1_000_000);
        /*
        ArrayList head-insert (ms): 101
        LinkedList head-insert (ms): 4
        ArrayList get (ms): 0
        LinkedList get (ms): 333
         */


    }
}
