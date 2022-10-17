package com.feng.order;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;
import com.feng.order.catutil.CatRestInterceptor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

/**
 * @author f
 * @date 2022/10/15 16:40
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.feng")
@RestController
@Log4j2
public class CatOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatOrderApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    @Autowired
    private RestTemplate restTemplate;

    /** Account 账号服务 */
    @Value("${service3.address:localhost:8083}")
    private String serviceAddress3;

    /** stock 库存服务 */
    @Value("${service4.address:localhost:8084}")
    private String serviceAddress4;

    /** 测试异常端口 */
    private static final int MOCK_PORT = 8765;

    /**
     * 提供下单接口
     * @return res
     * @throws InterruptedException ex
     */
    @RequestMapping("/order")
    public String service2MethodInController() throws InterruptedException {
        Thread.sleep(200);
        // 调用账户服务以及库存服务
        String service3 = restTemplate.getForObject("http://" + serviceAddress3 + "/account", String.class);
        String service4 = restTemplate.getForObject("http://" + serviceAddress4 + "/stock", String.class);
        return String.format("Calling order service[order success] ==> Calling Account Service [%s] ==> Calling Customer Service [%s]", service3, service4);
    }

    /**
     * 模拟异常超时接口
     * @return  res
     * @throws InterruptedException ex
     */
    @RequestMapping("/readtimeout")
    public String connectionTimeout() throws InterruptedException {
        Transaction t = Cat.newTransaction(CatConstants.TYPE_CALL, "connectionTimeout");
        Thread.sleep(500);
        try {
            log.info("Calling a missing service");
            restTemplate.getForObject("http://localhost:" + MOCK_PORT + "/readtimeout", String.class);
            return "Should blow up";
        } catch (Exception e) {
            t.setStatus(e);
            Cat.getProducer().logError(e);
            throw e;
        } finally {
            t.complete();
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(2000);
        clientHttpRequestFactory.setReadTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        // 保存和传递调用链上下文
        restTemplate.setInterceptors(Collections.singletonList(new CatRestInterceptor()));

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override public boolean hasError(ClientHttpResponse response)
                    throws IOException {
                try {
                    return super.hasError(response);
                } catch (Exception e) {
                    return true;
                }
            }

            @Override public void handleError(ClientHttpResponse response)
                    throws IOException {
                try {
                    super.handleError(response);
                } catch (Exception e) {
                    log.error("Exception [" + e.getMessage() + "] occurred while trying to send the request", e);
                    throw e;
                }
            }
        });
        return restTemplate;
    }
}
