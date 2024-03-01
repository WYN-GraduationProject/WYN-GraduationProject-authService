package com.constantineqaq.assembly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.constantineqaq","utils","config"})
@MapperScan({"com.constantineqaq.base.mapper"})
public class AuthAssemblyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthAssemblyApplication.class, args);
    }
}
