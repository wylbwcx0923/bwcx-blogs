package cn.mvpwyl.mybatis.plus;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author wangyuliang
 */
@SpringBootApplication
@MapperScan("cn.mvpwyl.mybatis.plus.dao")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
