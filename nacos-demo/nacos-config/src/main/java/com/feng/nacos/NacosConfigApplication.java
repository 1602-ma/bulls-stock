package com.feng.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f
 * @date 2022/10/23 16:45
 */
@SpringBootApplication
@RestController
@RefreshScope
public class NacosConfigApplication {

    @Value(value = "${stockName: 中国平安}")
    private String stockName;

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * 提供股票名称的访问接口
     * @return stock
     */
    @RequestMapping("/getStockName")
    public String getStockName() {
        return "股票名称： " + stockName;
    }
}
