/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.cn.seg.impl;

import org.yqm.nlp.cn.seg.ISegTagger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huangzixuan
 */
public class MixSegTagger implements ISegTagger {
    List<ISegTagger> seggers;
    
    public MixSegTagger(List<ISegTagger> seggers) {
        this.seggers = seggers;
    }

    @Override
    public List<String> seg2List(String text) {
        List<String> mixWords = new ArrayList<>();
        for(ISegTagger segger : seggers){
            mixWords.addAll(segger.seg2List(text));
        }
        return mixWords;
    }

    @Override
    public String seg2String(String text) {
        String segString = "";
        for(ISegTagger segger : seggers){
            segString += segger.seg2String(text) + " ";
        }
        return segString.trim();
    }

    @Override
    public String[] seg2Array(String text) {
        String segString = seg2String(text);
        return segString.split(" ");
    }
}
