/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.ie.pattern.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * a pattern based entity extractor
 * @author hzx
 */
public class PatternUtil {
    public static List<String[]> extractViaRegexWithTags(PatternInfo patInfo, String text) {
        Pattern pattern = patInfo.getPattern();
        List<String> tags = patInfo.patTags;
        Matcher matcher = pattern.matcher(text);
        List<String[]> results = new ArrayList<>();
        if(matcher.find()) {
            for(int i=0; i<tags.size(); i++) {
                String[] item = new String[2];
                item[0] = matcher.group(i+1);
                item[1] = tags.get(i);
                results.add(item);
            }
        }
        return results;
    }
    
    public static ExtractInfo extractInfoViaRegexWithTags(PatternInfo patInfo, String text) {
        ExtractInfo extInfo = new ExtractInfo();
        extInfo.indexs = new ArrayList<>();
        extInfo.extractedPhrases = new ArrayList<>();
        extInfo.tags = patInfo.patTags;
        extInfo.matchedPattern = patInfo;
        Pattern pattern = patInfo.getPattern();
        List<String> tags = patInfo.patTags;
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()) {
            for(int i=0; i<tags.size(); i++) {
                int[] index = new int[2];
                index[0]= matcher.start(i+1);
                index[1] = matcher.end(i+1);
                extInfo.indexs.add(index);
                extInfo.extractedPhrases.add(matcher.group(i+1));
            }
        }
        return extInfo;
    }
    
    public static String tagViaRegex(PatternInfo patInfo, String text) {
        Pattern pattern = patInfo.getPattern();
        List<String> tags = patInfo.patTags;
        Matcher matcher = pattern.matcher(text);
        String result = "";
        int start, end;
        end = 0;
        if(matcher.find()) {
            for(int i=0; i<tags.size(); i++) {
                start = matcher.start(i+1);
                result += text.substring(end, start);
                result += " ";
                end = matcher.end(i+1);
                result += text.substring(start, end) + "[" + tags.get(i) + "] ";
            }
        }
        result += text.substring(end);
        return result.trim();
    }
}
