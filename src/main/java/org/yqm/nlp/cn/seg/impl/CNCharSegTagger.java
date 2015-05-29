/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.cn.seg.impl;

import org.yqm.nlp.cn.Chars;
import org.yqm.nlp.cn.Chars.CharType;
import org.yqm.nlp.cn.Chars.StringType;
import org.yqm.nlp.cn.seg.ISegTagger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * seg string to chinese charactors (number or EN words will not split)
 * @author hzx
 */
public class CNCharSegTagger implements ISegTagger {

    @Override
    public List<String> seg2List(String text) {
        String[] wordsArray = seg2Array(text);
        List<String> wordsList = Arrays.asList(wordsArray);
        return wordsList;
    }

    @Override
    public String seg2String(String text) {
        String words = "";
        String[] wordsArray = seg2Array(text);
        for(String word : wordsArray) {
            words += " " + word;
        }
        return words.trim();
    }

    @Override
    public String[] seg2Array(String text) {
        String[][] data = genSequence(text);
        return data[0];
    }
    
    public static String[][] genSequence(String sent){
        CharType[] tags = Chars.getType(sent);
        int len = sent.length();
        List<String> words = new ArrayList<>();
        List<String> types = new ArrayList<>();
        int begin =0;
        for(int j=0; j<len; j++) {			
            if(j<len-1 && tags[j]==CharType.L && tags[j+1]==CharType.L){//当前是连续英文
                continue;
            }else if(j<len-1 &&tags[j]==CharType.D && tags[j+1]==CharType.D){//当前是连续数字
                continue;
            }
            StringType st = Chars.char2StringType(tags[j]);
            String w = sent.substring(begin,j+1);
            words.add(w);
            types.add(st.toString());
            begin = j+1;
        }		
        String[][] data = new String[2][];
        data[0] = words.toArray(new String[words.size()]);
        data[1] = types.toArray(new String[types.size()]);
        return data;
    }
}
