package com.feng.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f
 * @date 2022/10/12 23:11
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class StockUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockUserApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }
}
