package Day2.SECTION4_HIGH_LEVEL_CONCURRENCY_UTILS.BLOCKING_QUEUE;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(5);
        Runnable producer=()-> {
            for(int i=0;i<20;i++){
                try{
                    queue.put(i);// blocks automatically if queue is full — no manual wait()/notify() needed
                    System.out.println("Produced "+i);
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };
        Runnable consumer=()->{
            for(int i=0;i<20;i++){
                try{
                    int value=queue.take();//blocks automatically if queue is empty
                    System.out.println("Consumed "+ value);
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}/*
Consumed 0
Produced 0
Produced 1
Consumed 1
Produced 2
Consumed 2
Produced 3
Consumed 3
Produced 4
Consumed 4
Produced 5
Consumed 5
Produced 6
Produced 7
Consumed 6
Produced 8
Consumed 7
Consumed 8
Produced 9
Consumed 9
Produced 10
Consumed 10
Produced 11
Consumed 11
Consumed 12
Produced 12
Produced 13
Consumed 13
Produced 14
Consumed 14
Produced 15
Consumed 15
Consumed 16
Produced 16
Produced 17
Consumed 17
Produced 18
Consumed 18
Produced 19
Consumed 19
*/
