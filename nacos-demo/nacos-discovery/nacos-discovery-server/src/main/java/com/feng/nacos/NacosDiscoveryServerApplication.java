package com.feng.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author f
 * @date 2022/10/23 17:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryServerApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * 提供查询股票价格的接口
     * @param name name
     * @return price
     */
    @RequestMapping("/getStockPrice")
    public String getStockPrice(@RequestParam(defaultValue = "中国平安") String name) {
        System.out.println("=========================>>>>");
        return "股票名称： " + name + ", 股票价格： " + (new Random().nextInt(100 -20) + 20);
    }
}
