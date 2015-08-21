/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.util.map;

import java.util.Map;

/**
 * get the max|min entry from Map
 * @author hzx
 */
public class MaxUtil {
    /**
     * get entry with max value
     * @param <T>
     * @param scoreMap
     * @return 
     */
    public static <T extends Comparable<T>> Map.Entry<String, T> getMaxEntry(Map<String, T> scoreMap) {
        Map.Entry<String, T> maxEntry = null;
        for(Map.Entry<String, T> entry : scoreMap.entrySet()) {
            if(maxEntry == null) {
                maxEntry = entry;
                continue;
            }
            if(maxEntry.getValue().compareTo(entry.getValue()) < 0) {
                maxEntry = entry;
            }
        }
        
        return maxEntry;
    }
    
    /**
     * get the entry with minimal value
     * @param <T>
     * @param scoreMap
     * @return 
     */
    public static <T extends Comparable<T>> Map.Entry<String, T> getMinEntry(Map<String, T> scoreMap) {
        Map.Entry<String, T> minEntry = null;
        for(Map.Entry<String, T> entry : scoreMap.entrySet()) {
            if(minEntry == null) {
                minEntry = entry;
                continue;
            }
            if(minEntry.getValue().compareTo(entry.getValue()) > 0) {
                minEntry = entry;
            }
        }
        
        return minEntry;
    }
}
