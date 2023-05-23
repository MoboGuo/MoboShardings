package com.example.moboshardings;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.moboshardings.mapper")
public class MoboShardingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoboShardingsApplication.class, args);
    }

}
