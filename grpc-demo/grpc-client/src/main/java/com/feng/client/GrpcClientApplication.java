package com.feng.client;

import com.feng.client.service.GrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f
 * @date 2022/10/22 0:01
 */
@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.feng")
public class GrpcClientApplication {

    @Autowired
    private GrpcClientService grpcClientService;

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    @RequestMapping("/getStockPrice")
    public String getStockPrice(@RequestParam(defaultValue = "中国平安") String name) {
        return grpcClientService.getStockPrice(name);
    }
}
