package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestList {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("111", "222", "333", "444", "555");
        List<String> list2 = Arrays.asList("222", "333", "444", "555", "666");

        Map<String, String> map = new HashMap<>();
        list1.forEach(item -> {
            if (!list2.contains(item)) {
                map.put("del", item);
            }
        });

        list2.forEach(item -> {
            if (!list1.contains(item)) {
                map.put("add", item);
            }
        });

        System.out.println(map);


    }

}
