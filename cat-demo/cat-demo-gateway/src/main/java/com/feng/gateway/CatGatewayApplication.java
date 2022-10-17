package com.feng.gateway;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;
import com.feng.gateway.catutil.CatRestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author f
 * @date 2022/10/15 16:14
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.feng")
@RestController
public class CatGatewayApplication {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CatGatewayApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 保存和传递调用链上下文
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));
        return restTemplate;
    }

    /**
     * 指定订单服务的接口
     */
    @Value("${service2.address:localhost:8082}")
    private String serviceAddress;

    /**
     * 模拟正常的请求
     * @return res
     * @throws InterruptedException ex
     */
    @RequestMapping("/getway")
    public String gateway() throws Exception {
        Thread.sleep(100);
        String response = restTemplate.getForObject("http://" + serviceAddress + "/order", String.class);
        return "gateway response ==> " + response;
    }

    /**
     * 模拟一个请求异常
     * @return res
     * @throws InterruptedException ex
     */
    @RequestMapping("/timeout")
    public String timeout() throws InterruptedException {
        Transaction t = Cat.newTransaction(CatConstants.TYPE_URL, "timeout");
        try {
            Thread.sleep(100);
            String response = restTemplate.getForObject("http://" + serviceAddress + "/timeout", String.class);
            return response;
        } catch (Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
            throw e;
        } finally {
            t.complete();
        }
    }
}
