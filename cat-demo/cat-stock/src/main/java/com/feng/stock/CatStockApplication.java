package com.feng.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f
 * @date 2022/10/17 21:47
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.feng")
@RestController
public class CatStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatStockApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    @RequestMapping("/stock")
    public String stock() throws InterruptedException {
        Thread.sleep(200);
        return "stock success";
    }
}
