package com.feng.ceph;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author f
 * @date 2022/10/24 22:27
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.feng")
public class CephApplication {

    public static void main(String[] args) {
        System.out.println("start----------------------------------------up");
        String username = "admin";
        String monIp = "192.168.211.120:67889;192.168.211.121:6789;192.168.211,122:6789";
        String userKey = "s";
        String mountPath = "/";
    }
}
