package SESSION3_COLLECTIONS.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    record SwiftMessage(String id, int priority){}
    public static void main(String[] args) {
        PriorityQueue<SwiftMessage> queue = new PriorityQueue<>(Comparator.comparingInt(SwiftMessage::priority));

        queue.add(new SwiftMessage("MT548-1", 3)); 
        queue.add(new SwiftMessage("MT548-2", 1));
        queue.add(new SwiftMessage("MT548-3", 2));

        while (!queue.isEmpty()) {
            SwiftMessage message = queue.poll();
            System.out.println("Processing message: " + message.id() + " with priority: " + message.priority());
        }
        /*
        Processing message: MT548-2 with priority: 1
        Processing message: MT548-3 with priority: 2
        Processing message: MT548-1 with priority: 3
         */
    }
}
