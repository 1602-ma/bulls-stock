package com.feng.cat.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f
 * @date 2022/10/13 22:06
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.feng")
@RestController
public class CatAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatAccountApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * 提供账户服务接口
     * @return                      String
     * @throws InterruptedException ex
     */
    @RequestMapping("/account")
    public String account() throws InterruptedException {
        Thread.sleep(150);
        return "account success";
    }
}
