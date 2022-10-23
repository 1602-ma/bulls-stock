package com.feng.seata.config;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author f
 * @date 2022/10/23 15:33
 */
@Configuration
public class SeataConfiguration {

    @Value("${spring.application.name}")
    private String applicationId;

    /**
     * ts
     * @return ts
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        GlobalTransactionScanner globalTransactionScanner = new GlobalTransactionScanner(applicationId, "my_test_tx_group");
        return globalTransactionScanner;
    }
}
