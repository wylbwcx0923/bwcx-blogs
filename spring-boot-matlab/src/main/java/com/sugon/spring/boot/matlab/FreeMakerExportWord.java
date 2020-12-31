package com.sugon.spring.boot.matlab;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMakerExportWord {

    public static void exportDoc() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("luoSsm", "100");
        dataMap.put("guangGzj", "101");
        dataMap.put("yeGlsyjl", "102");
        dataMap.put("yeGwj", "103.1");
        dataMap.put("yeGjyzj", "25.5");
        dataMap.put("yeGnj", "25.6");
        dataMap.put("yGhd", "22");
        dataMap.put("faLhd", "109");
        dataMap.put("gunZzcbj", "111");
        dataMap.put("yGjxwj2", "56");
        dataMap.put("yGjxwj", "57");
        dataMap.put("yeGjxwj2", "58");
        dataMap.put("luoSjlaqxs", "1000");
        dataMap.put("yeGjxwj", "1000");
        dataMap.put("divNum", "1000");
        dataMap.put("luoStjmaqxs", "1000");
        dataMap.put("minFactor", "1000");
        dataMap.put("minFactor2", "1000");
        dataMap.put("guangGzdyl", "1000");

        //经过编码后的图片路径
        String VolumePath = "/Users/wangyuliang/Desktop/zcyp-word/Volume.png";
        String VolumeImage = getWatermarkImage(VolumePath);
        dataMap.put("Volume", VolumeImage);

        String ElementPath = "/Users/wangyuliang/Desktop/zcyp-word/Element.png";
        String ElementImage = getWatermarkImage(ElementPath);
        dataMap.put("Element", ElementImage);

        //封装表格的数据
        List<Map<String,String>> luoWplList=new ArrayList<>();

        for (int i = 0; i <= 15; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("Angle",i+"");
            map.put("Damage",(2*i)+"");
            map.put("factor",(3*i)+"");
            luoWplList.add(map);
        }
        dataMap.put("luoWplList",luoWplList);

        List<Map<String,String>> luoSjmplList=new ArrayList<>();

        for (int i = 0; i <= 15; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("Angle",i+"");
            map.put("Damage",(2*i)+"");
            map.put("reserve",(3*i)+"");
            luoSjmplList.add(map);
        }
        dataMap.put("luoSjmplList",luoSjmplList);


        //Configuration用于读取ftl文件
        Configuration configuration = new Configuration(new Version("2.3.0"));
        configuration.setDefaultEncoding("utf-8");

        Writer out = null;
        try {
            //输出文档路径及名称
            File outFile = new File("/Users/wangyuliang/Desktop/中材叶片报告.docx");
            out = new BufferedWriter(new OutputStreamWriter(new
                    FileOutputStream(outFile), StandardCharsets.UTF_8), 10240);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 加载文档模板
        Template template;
        try {
            //指定路径，例如C:/a.ftl 注意：此处指定ftl文件所在目录的路径，而不是ftl文件的路径
            configuration.setDirectoryForTemplateLoading(new File("/Users/wangyuliang/Desktop/zcyp-word"));
            //以utf-8的编码格式读取文件
            template = configuration.getTemplate("zcyp_word_t.ftl", "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件模板加载失败！", e);
        }

        // 填充数据
        try {
            template.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new RuntimeException("模板数据填充异常！", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("模板数据填充异常！", e);
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("文件输出流关闭异常！", e);
                }
            }
        }
    }

    /***
     * 处理图片
     * @param watermarkPath 图片路径  D:/image.png
     * @return
     */
    private static String getWatermarkImage(String watermarkPath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(watermarkPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    public static void main(String[] args) {
        exportDoc();
    }
}
