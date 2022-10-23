package com.feng.seata;

import com.feng.seata.entity.AssetAssign;
import com.feng.seata.service.IAssignService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author f
 * @date 2022/10/23 15:38
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.feng")
@EntityScan(basePackages = "com.feng")
@EnableJpaRepositories(basePackages = "com.feng")
@RestController
@EnableTransactionManagement
public class SeataApplication {

    final String ASSET_ID = "14070e0e3cfe403098fa9ca37e8e7e76";

    @Autowired
    private IAssignService assignService;

    public static void main(String[] args) {
        SpringApplication.run(SeataApplication.class, args);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("                           start success                                      ");
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Home string.
     *
     * @return the string
     */
    @RequestMapping(value = "/asset/assign")
    @ResponseBody
    public String assetAssign() {

        String result;
        try {
            AssetAssign assetAssign = assignService.increaseAmount(
                    ASSET_ID);
            result = assetAssign.toString();
        } catch (Exception e) {
            result = ExceptionUtils.getMessage(e);

        }
        return result;
    }
}
