package com.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableDiscoveryClient
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication

public class zuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(zuulApplication.class, args);
        System.out.println("ZUUL_PROXY_SERVER with status UP "+"\n");

    }
}
