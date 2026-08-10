package Day2.SECTION3_LOCKS.REWRITELOCK;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private final Map<String, String> cache=new HashMap<>();
    private final ReadWriteLock rwLock=new ReentrantReadWriteLock();
    String read(String key){
        rwLock.readLock().lock();// multiple threads can hold this concurrently
        try{
            return cache.get(key);
        }finally {
            rwLock.readLock().unlock();
        }
    }
    void write(String key, String value){
        rwLock.writeLock().lock();// only one thread can hold this at a time
        try{
            cache.put(key,value);
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Thread writer = new Thread(() -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    String key = "key-" + i;
                    String value = "value-" + i;
                    demo.write(key, value);
                    System.out.println("Writer wrote: " + key + "=" + value);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Writer");

        Runnable readerTask = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String key = "key-" + ((i % 3) + 1);
                    String value = demo.read(key);
                    System.out.println(Thread.currentThread().getName() + " read: " + key + "=" + value);
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread reader1 = new Thread(readerTask, "Reader-1");
        Thread reader2 = new Thread(readerTask, "Reader-2");

        writer.start();
        reader1.start();
        reader2.start();

        try {
            writer.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /*
    Writer wrote: key-1=value-1
Reader-2 read: key-2=null
Reader-1 read: key-2=null
Reader-1 read: key-3=null
Reader-2 read: key-3=null
Writer wrote: key-2=value-2
Reader-2 read: key-1=value-1
Reader-1 read: key-1=value-1
Writer wrote: key-3=value-3
Reader-1 read: key-2=value-2
Reader-2 read: key-2=value-2
Reader-2 read: key-3=value-3
Reader-1 read: key-3=value-3
     */

}
