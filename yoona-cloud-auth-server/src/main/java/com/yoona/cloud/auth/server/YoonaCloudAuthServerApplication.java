package com.yoona.cloud.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author YoonaDa
 */
@MapperScan("com.yoona.cloud.auth.server.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class YoonaCloudAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoonaCloudAuthServerApplication.class, args);
    }

}
