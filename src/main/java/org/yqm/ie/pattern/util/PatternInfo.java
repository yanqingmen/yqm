/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.ie.pattern.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * infomation of Pattern
 * @author hzx
 */
public class PatternInfo {
    String patString;
    String patId;
    Pattern pat;
    float patScore;
    List<String> patTags;
    //extra infos
    Map<String, Object> extraInfos;
    
    /**
     * @param patString original regular expression string of pattern
     * @param patId pattern id
     * @param pat compiled regex pattern
     * @param patScore score of pattern (infer the importance of a pattern)
     * @param patTags  tag for entitys(substring in regular expression)  in pattern
     */
    public PatternInfo(String patString, String patId, Pattern pat, float patScore, List<String> patTags) {
        this.patString = patString;
        this.patId = patId;
        this.pat = pat;
        this.patScore = patScore;
        this.patTags = patTags;
    }
    
    /**
     * set extra info
     * @param key
     * @param info 
     */
    public synchronized void setExtraInfo(String key, Object info) {
        if(extraInfos==null) {
            extraInfos = new HashMap<>();
        }
        extraInfos.put(key, info);
    }
    
    /**
     * get extra info via key
     * @param key
     * @return 
     */
    public Object getExtraInfo(String key) {
        return extraInfos.get(key);
    }
    
    /**
     * get original regular expression string of pattern
     * @return 
     */
    public String getPatString() {
        return patString;
    }
    
    /**
     * get pattern id
     * @return 
     */
    public String getPatId() {
        return patId;
    }
    
    /**
     * get compiled regex pattern
     * @return 
     */
    public Pattern getPattern() {
        return pat;
    }
    
    /**
     * get pattern score
     * @return 
     */
    public float getPatScore() {
        return patScore;
    }
    
    /**
     * get tag for entitys(substring in regular expression)  in pattern
     * @return 
     */
    public List<String> getPatTags() {
        return patTags;
    }
}
