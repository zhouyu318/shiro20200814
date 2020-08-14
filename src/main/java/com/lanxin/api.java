package com.lanxin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lanxin.dao")
public class api {
    public static void main(String[] args) {
        SpringApplication.run(api.class,args);
    }
}
