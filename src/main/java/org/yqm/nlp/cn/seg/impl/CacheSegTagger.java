/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.cn.seg.impl;

import org.yqm.nlp.cn.seg.ISegTagger;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import com.google.common.cache.LoadingCache;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author huangzixuan
 */
public class CacheSegTagger implements ISegTagger {
    private static final Log logger = LogFactory.getLog(CacheSegTagger.class);
    
    ISegTagger segger;
    LoadingCache<String,String> cache;
    
    public CacheSegTagger(ISegTagger segger) {
        this.segger = segger;
        cache = CacheBuilder.newBuilder()
                .maximumSize(5000)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(
                    new CacheLoader<String,String>(){

                    @Override
                    public String load(String text) throws Exception {
                        return seg4cache(text);
                    }                    
                });
    }
    
    public CacheSegTagger(ISegTagger segger, int cacheSize, int expTime) {
        this.segger = segger;
        cache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterAccess(expTime, TimeUnit.MINUTES)
                .build(
                    new CacheLoader<String,String>(){

                    @Override
                    public String load(String text) throws Exception {
                        return seg4cache(text);
                    }             
                });
    }
    
    private String seg4cache(String text) {
        return segger.seg2String(text);
    }

    @Override
    public List<String> seg2List(String text) {
        String[] seggedArray = seg2Array(text);
        return Arrays.asList(seggedArray);
    }

    @Override
    public String seg2String(String text) {
        try {
            return cache.get(text);
        } catch (ExecutionException ex) {
            logger.error(ex);
            return "";
        }
    }

    @Override
    public String[] seg2Array(String text) {
        String seggedText = seg2String(text);
        return seggedText.split(" ");
    }
    
}
