/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.cn.seg.impl;

import org.yqm.nlp.cn.seg.ISegTagger;
import org.yqm.nlp.data.reader.DataReader;
import org.yqm.nlp.types.Instance;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.trie4j.doublearray.DoubleArray;
import org.trie4j.patricia.PatriciaTrie;

/**
 * SegTagger based given dict,use max match strategy and double array trie for fast search.
 * @author huangzixuan
 */
public class DictBasedSegTagger implements ISegTagger {
    
    DoubleArray keyWordsTrie;
    
    public DictBasedSegTagger(DataReader reader) {
        PatriciaTrie paTrie = new PatriciaTrie();
        String word;
        while(reader.hasNext()){
            Instance inst = reader.next();
            word = (String) inst.getData();
            if(word.isEmpty()) {
                continue;
            }
            paTrie.insert(word);
        }
        
        keyWordsTrie = new DoubleArray(paTrie);
    }

    @Override
    public List<String> seg2List(String text) {
        List<String> words = Arrays.asList(seg2Array(text));
        return words;
    }

    @Override
    public String seg2String(String text) {
        String seggedText = "";
        Iterator<String> resultIter;
        String word;
        int index = 0;
        while(index<text.length()){
            word = null;
            resultIter = keyWordsTrie.commonPrefixSearch(text.substring(index)).iterator();
            while(resultIter.hasNext()){
                word = resultIter.next();
            }
            if(word==null) {
                seggedText += text.substring(index,index+1);
                index++;
            }
            else {
                seggedText += word;
                index += word.length();
            }
            seggedText += " ";
        }
        
        return seggedText.trim();
    }

    @Override
    public String[] seg2Array(String text) {
        return seg2String(text).split(" ");
    }
    
}
