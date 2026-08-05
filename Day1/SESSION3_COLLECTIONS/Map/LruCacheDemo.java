package SESSION3_COLLECTIONS.Map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheDemo extends LinkedHashMap<String, String> {
    private final int capacity;
    public LruCacheDemo(int capacity) {
         super(16, 0.75f, true); // access-order mode
        this.capacity = capacity;
    }
@Override
protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {    
    return size() > capacity;
}

public static void main(String[] args) {
    LruCacheDemo cache = new LruCacheDemo(3);
     cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3");
        cache.get("A");
        cache.put("D", "4");
        System.out.println(cache.keySet()); // [C, A, D] — B evicted, A survived due to recent access
}
}
