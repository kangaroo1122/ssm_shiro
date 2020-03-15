package com.kangaroohy.ssm_shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kangaroohy.ssm_shiro.mapper")
public class SsmShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmShiroApplication.class, args);
    }

}
