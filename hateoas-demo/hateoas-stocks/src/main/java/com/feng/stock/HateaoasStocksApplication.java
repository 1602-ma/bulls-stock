package com.feng.stock;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author f
 * @date 2022/10/20 23:03
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.feng")
@EntityScan(basePackages = "com.feng")
@EnableJpaRepositories(basePackages = "com.feng")
public class HateaoasStocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateaoasStocksApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Hibernate的初始化
     * @return module
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * 用于json数据的封装
     * @return builder
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.indentOutput(true);
        };
    }
}
