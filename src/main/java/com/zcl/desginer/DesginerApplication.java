package com.zcl.desginer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DesginerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesginerApplication.class, args);
    }

}
