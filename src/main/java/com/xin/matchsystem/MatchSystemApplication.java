package com.xin.matchsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xin.matchsystem.mapper")
public class MatchSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchSystemApplication.class, args);
    }

}
