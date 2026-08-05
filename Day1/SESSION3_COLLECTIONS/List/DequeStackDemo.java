package SESSION3_COLLECTIONS.List;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeStackDemo {
public static void main(String[] args) {
    Deque<String> stack = new ArrayDeque<>();
    stack.push("First");
    stack.push("Second");
    System.out.println(stack.pop()); // Outputs: Second LIFO
}
}
