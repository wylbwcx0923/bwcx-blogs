package com.bwcx.param;

import com.alibaba.druid.support.json.JSONUtils;
import com.bwcx.linux.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        String parentPath = "/Users/wangyuliang/Desktop/后处理代码和提取的结果文件";
        Map<String, String> mnt1Map = formatResByFilePath(parentPath + "/stress_nut1.txt");
        Map<String, String> mnt2Map = formatResByFilePath(parentPath + "/stress_nut2.txt");
        Map<String, String> blade1Map = formatResByFilePath(parentPath + "/stress_blade1.txt");
        Map<String, String> blade2Map = formatResByFilePath(parentPath + "/stress_blade2.txt");

        System.out.println(mnt2Map);
        List<Map<String, String>> list = new ArrayList<>();

        double total = -1;
        for (int i = 0; i < 21; i++) {
            Map<String, String> map = new HashMap<>();
            String bending = String.format("%.1f", total);
            map.put("bending", bending + "*M");
            String key = String.valueOf(Double.parseDouble(bending.replaceAll("-", "")) + 1);
            if (Double.parseDouble(bending) < 0) {
                map.put("blade", blade2Map.get(key));
                map.put("mnt", mnt2Map.get(key));
            } else if (Double.parseDouble(bending) == 0.0) {
                map.put("bending", "0*M");
                double blade0 = (Double.parseDouble(blade1Map.get("1.0")) + Double.parseDouble(blade2Map.get("1.0"))) / 2;
                double mnt0 = (Double.parseDouble(mnt1Map.get("1.0")) + Double.parseDouble(mnt2Map.get("1.0"))) / 2;
                map.put("blade", String.format("%.2f", blade0));
                map.put("mnt", String.format("%.2f", mnt0));
            } else {
                map.put("blade", blade1Map.get(key));
                map.put("mnt", mnt1Map.get(key));
            }

            total += 0.1;
            list.add(map);
        }

        String jsonList = JSONUtils.toJSONString(list);
        System.out.println(jsonList);
    }


    /**
     * 将txt文件解析成Map
     *
     * @param filePath
     * @return
     */
    private static Map<String, String> formatResByFilePath(String filePath) {
        String fileContent = FileUtils.readFileContent(filePath);
        //把6个空格的替换成逗号,然后再去除所有的空格
        String trimContent = fileContent.replaceAll("      ", ",").replaceAll(" ", "");
        //把参数转成数组
        String[] contentAry = trimContent.split("\n");
        //创建Map,遍历赋值
        Map<String, String> map = new HashMap<String, String>();
        for (String item : contentAry) {
            String[] itemAry = item.split(",");
            map.put(String.format("%.1f", Double.valueOf(itemAry[0])), itemAry[1]);
        }

        return map;
    }

}
