package com.sugno.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatTest {

    public static void main(String[] args) {
        String fileName="/Users/wangyuliang/fsdownload/job_FLUENT_0730_095052_end.dat";
        readTxtFile(fileName);
    }

    public static void readTxtFile(String filePath){
        try {
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(new String(lineTxt.getBytes(),"GBK"));
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
}
