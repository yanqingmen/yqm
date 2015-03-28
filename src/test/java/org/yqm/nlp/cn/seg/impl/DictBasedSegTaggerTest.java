/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.cn.seg.impl;

import org.yqm.nlp.cn.seg.ISegTagger;
import org.yqm.nlp.data.reader.DataReader;
import org.yqm.nlp.data.reader.StringDataReaderFactory;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author huangzixuan
 */
public class DictBasedSegTaggerTest extends TestCase{
    @Test
    public void testSegFunctions() {
        List<String> words = Arrays.asList("这是","一","一个","测试","百日维新","维新","运动水杯","","\n");
        DataReader reader = StringDataReaderFactory.create(words);
        
        ISegTagger segger = new DictBasedSegTagger(reader);
        
        String text0 = "          ";
        String text1 = "";
        String text2 = "这是一个测试";
        String text3 = "这是一个运动水杯。";
        
        System.out.println(segger.seg2String(text0));
        System.out.println(segger.seg2String(text1));
        System.out.println(segger.seg2String(text2));
        System.out.println(segger.seg2String(text3));
    }
}
