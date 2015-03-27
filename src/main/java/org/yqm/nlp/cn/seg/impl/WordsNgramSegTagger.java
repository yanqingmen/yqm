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
public class WordsNgramSegTagger implements ISegTagger{
    ISegTagger baseSegger;
    int N;
    
    public WordsNgramSegTagger(ISegTagger baseSegger, int N) {
        this.baseSegger = baseSegger;
        this.N = N;
    }

    @Override
    public List<String> seg2List(String text) {
        String[] words = baseSegger.seg2Array(text);
        List<String> ngrams = new ArrayList<>();
        for(int i=0; i<=words.length-N; i++){
            String token = "";
            for(int j=i; j<i+N; j++){
                token += words[j];
            }
            ngrams.add(token);
        }
        return ngrams;
    }

    @Override
    public String seg2String(String text) {
        String[] words = baseSegger.seg2Array(text);
        String ngrams = "";
        for(int i=0; i<=words.length-N; i++){
            String token = "";
            for(int j=i; j<i+N; j++){
                token += words[j];
            }
            ngrams += token + " ";
        }
        return ngrams.trim();
    }

    @Override
    public String[] seg2Array(String text) {
        return seg2String(text).split(" ");
    }
    
}
