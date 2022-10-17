package com.feng.order.catutil;

import java.util.HashMap;
import java.util.Map;

import com.dianping.cat.Cat;

/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
 */
public class CatContext implements Cat.Context {
    /**
     * 存储链路监控相关信息
     */
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
