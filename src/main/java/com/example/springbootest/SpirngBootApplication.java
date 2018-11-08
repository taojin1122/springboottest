package com.example.springbootest;

import com.example.springbootest.study.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpirngBootApplication {
    public static void main(String[] args) {
        //通过SpringApplication 启动整个应用程序
        SpringApplication.run(SpringBootApplication.class,args);
    }
}
