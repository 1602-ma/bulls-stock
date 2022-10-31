package com.feng.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author f
 * @date 2022/10/31 21:41
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        System.out.println("---------------------------- start -------------------------------");

        try {
            List<String> warnings = new ArrayList<>();
            boolean overwrite = true;
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(GeneratorApplication.class.getClassLoader().getResourceAsStream("generatorConfig.xml"));
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            if (warnings.size() > 0) {
                System.out.println("Warnning Msg : ");
                for (String warn : warnings) {
                    System.out.println(warn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------- end -------------------------------");
    }
}
