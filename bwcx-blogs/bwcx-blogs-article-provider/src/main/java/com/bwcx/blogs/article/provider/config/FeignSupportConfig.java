package com.bwcx.blogs.article.provider.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


//文件配置类
@Configuration
public class FeignSupportConfig {
    /**
     * Feign Spring 表单编码器
     * @return 表单编码器
     */
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartEncoder(){
        return new SpringFormEncoder();
    }
}