package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Hello world!
 */
@SpringBootApplication
public class App implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("-----> start success...");
    }
}
