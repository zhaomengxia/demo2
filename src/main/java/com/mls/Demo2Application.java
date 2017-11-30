package com.mls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ASUS on 2017/11/27.
 */
@SpringBootApplication
//扫描包
@ComponentScan("com.mls")
public class Demo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}
