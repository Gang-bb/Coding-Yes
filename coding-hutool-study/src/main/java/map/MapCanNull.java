package map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试hashmap和ConcurrentHashMap的key是否可以为null
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class MapCanNull {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>(1);
        HashMap<Object, Object> hashMap = new HashMap<>(1);
        concurrentHashMap.put("", "");
        hashMap.put("", "");
        //concurrentHashMap.put(null, "");
        hashMap.put(null, "");
    }
}
