package com.bwcx.blogs.fastdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FastdfsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastdfsApplication.class, args);
    }
}
