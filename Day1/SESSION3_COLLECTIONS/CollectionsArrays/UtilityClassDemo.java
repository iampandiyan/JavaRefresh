package SESSION3_COLLECTIONS.CollectionsArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UtilityClassDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(5, 3, 8, 1, 9));
        Collections.sort(nums);
        System.out.println(nums);
        System.out.println(Collections.binarySearch(nums, 8));

        List<Integer> readOnly = Collections.unmodifiableList(nums);
        try{
            readOnly.add(10); // This will throw UnsupportedOperationException
        } catch(UnsupportedOperationException e){
            System.out.println("Caught UOE — cannot modify read-only list");
        }

        List<Integer> threadSafeList = Collections.synchronizedList(new ArrayList<>());
        int[] arr={5,3,8,1,9};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.binarySearch(arr, 8));

        List<Integer> fromArray = Arrays.asList(1, 2, 3);
        try {
            fromArray.add(4);
        } catch (UnsupportedOperationException e) {
            System.out.println("Arrays.asList() is fixed-size, cannot add/remove");
        }

    }

}
