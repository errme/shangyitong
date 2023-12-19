package com.shangguigu.shangyitong.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@Enableswagger
//@MapperScan("com.shangguigu.shangyitong.common")

@ComponentScan(basePackages = "com.shangguigu")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.shangguigu")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }

}
