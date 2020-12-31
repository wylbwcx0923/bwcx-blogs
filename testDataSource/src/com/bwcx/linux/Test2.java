package com.bwcx.linux;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) {
        String fileContent = FileUtils.readFileContent("/Users/wangyuliang/Desktop/后处理代码和提取的结果文件/stress_blade1.txt");
        String trimContent = fileContent.replaceAll("      ", ",").replaceAll(" ", "");
        System.out.println(trimContent);
        String[] contentAry = trimContent.split("\n");
        Map<String, Object> map = new HashMap<>();
        Arrays.stream(contentAry).forEach(arg -> {
            String[] argAry = arg.split(",");
            map.put(argAry[0], argAry[1]);
        });

        Set<String> strings = map.keySet();
        strings.forEach(e -> {
            System.out.println(e + "=" + map.get(e));
        });
    }
}
