/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.util.map;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * a util class to add item to Map<String, Number> 
 * @author hzx
 */
public class AddUtil {
    private static final Log logger = LogFactory.getLog(AddUtil.class);
    
    /**
     * add two number, only support Int, Long, Float ,Double
     * @param <T>
     * @param num1
     * @param num2
     * @return 
     */
    public static <T extends Number> T addNum(T num1, T num2) {
        if(num1.getClass() == Integer.class) {
            return (T) (Integer) ((Integer) num1 + (Integer) num2);
        }
        if(num1.getClass() == Long.class) {
            return (T) (Long) ((Long) num1 + (Long) num2);
        }
        if(num1.getClass() == Float.class) {
            return (T) (Float) ((Float) num1 + (Float) num2);
        }
        if(num1.getClass() == Double.class) {
            return (T) (Double) ((Double) num1 + (Double) num2);
        }
        else {
            throw new UnsupportedOperationException("addNum func only support Int, Long, Float ,Double");
        }
    }
    
    /**
     * add new item to scoremap, if key is already in scoreMap, value will be added;
     * @param <T>
     * @param scoreMap
     * @param key
     * @param value 
     */
    public static <T extends Number> void addNewItem(Map<String, T> scoreMap, String key, T value) {
        if(!scoreMap.containsKey(key)) {
            scoreMap.put(key, value);
        } else {
            T score = scoreMap.get(key);
            T sum = addNum(score, value);
            scoreMap.put(key, sum);
        }
    }
}
