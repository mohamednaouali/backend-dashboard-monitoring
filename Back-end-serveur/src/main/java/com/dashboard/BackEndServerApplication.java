package com.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *  --------------------> BackEndServerApplication
 */

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class BackEndServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndServerApplication.class, args);
    }

}
