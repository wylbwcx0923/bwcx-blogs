package com.nine.one.yuedu.read;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wangyuliang
 * spring boot项目的启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.nine.one.yuedu.read.mapper")
public class NineOneYueDuApplication {

    public static void main(String[] args) {
        SpringApplication.run(NineOneYueDuApplication.class, args);
    }

}
