package com.feng.cat.account.catutils;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author f
 * @date 2022/10/13 21:31
 */
public class CatContext implements Cat.Context {
    /** 存储链路监控相关信息 */
    private Map<String, String> properties = new HashMap<>();

    @Override
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    @Override
    public String getProperty(String key) {
        return properties.get(key);
    }
}
