/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.util.map;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * sort Map entrys based on value and return queue
 * @author hzx
 */
public class SortUtil {
    /**
     * sort scoremap in ascend order
     * @param <T>
     * @param scoreMap
     * @return 
     */
    public static <T extends Comparable<T>> Queue<Map.Entry<String, T>> ascendSortScoreMap(Map<String, T> scoreMap){
        Comparator<Map.Entry<String, T>> comp = new Comparator<Map.Entry<String, T>>(){
            @Override
            public int compare(Map.Entry<String, T> o1, Map.Entry<String, T> o2) {                
                return o1.getValue().compareTo(o2.getValue());
            }  
        };
        
        Queue<Map.Entry<String, T>> priorityQueue = new PriorityQueue<>(50, comp);
        
        for(Map.Entry<String, T> entry : scoreMap.entrySet()){
            priorityQueue.add(entry);
        }
        
        return priorityQueue;
    }
    
    /**
     * sort scoremap in descend order
     * @param <T>
     * @param scoreMap
     * @return 
     */
    public static <T extends Comparable<T>> Queue<Map.Entry<String, T>> descendSortScoreMap(Map<String, T> scoreMap){
        Comparator<Map.Entry<String, T>> comp = new Comparator<Map.Entry<String, T>>(){
            @Override
            public int compare(Map.Entry<String, T> o1, Map.Entry<String, T> o2) {                
                return o2.getValue().compareTo(o1.getValue());
            }  
        };
        
        Queue<Map.Entry<String, T>> priorityQueue = new PriorityQueue<>(50, comp);
        
        for(Map.Entry<String, T> entry : scoreMap.entrySet()){
            priorityQueue.add(entry);
        }
        
        return priorityQueue;
    }
}
