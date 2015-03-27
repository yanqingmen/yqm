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
public class CharNgramSegTagger implements ISegTagger {
    int N;
    
    public CharNgramSegTagger(int N) {
        this.N = N;
    }
    
    @Override
    public List<String> seg2List(String text) {
        List<String> ngrams = new ArrayList<>();
        for(int i=0; i<=text.length()-N; i++){
            ngrams.add(text.substring(i, i+N));
        }
        return ngrams;
    }

    @Override
    public String seg2String(String text) {
        String segText = "";
        for(int i=0; i<=text.length()-N; i++){
            segText += text.substring(i, i+N) + " ";
        }
        return segText.trim();
    }

    @Override
    public String[] seg2Array(String text) {
        return seg2String(text).split(" ");
    }
}
