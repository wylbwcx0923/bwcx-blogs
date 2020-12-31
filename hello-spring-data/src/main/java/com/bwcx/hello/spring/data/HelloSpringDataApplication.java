package com.bwcx.hello.spring.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@SpringBootApplication
@RestController
public class HelloSpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringDataApplication.class, args);
    }

    @GetMapping(value = "")
    public String test() {
        String base64Str = imageToBase64("/Users/wangyuliang/Desktop/后处理代码和提取的结果文件/Boundaries.png");
        String imgSrc = "data:image/png;base64," + base64Str;
        return "<img src='" + imgSrc + "' />";
    }


    /**
     * 把图片转化为BASE64格式
     *
     * @param imgPath
     * @return
     */
    private String imageToBase64(String imgPath) {
        byte[] data = null;
        try (InputStream in = new FileInputStream(imgPath)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回Base64编码过的字节数组字符串
        return Base64.getEncoder().encodeToString(data);
    }
}
