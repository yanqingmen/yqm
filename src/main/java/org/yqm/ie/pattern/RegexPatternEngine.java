/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.ie.pattern;

import org.yqm.ie.pattern.util.ExtractInfo;
import org.yqm.ie.pattern.util.PatternInfo;
import org.yqm.ie.pattern.util.PatternUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * PatternEngine based on regex
 * @author hzx
 */
public class RegexPatternEngine implements IPatternEngine{
    PatternMatch matcher;
    Map<String, PatternInfo> id2info;
    
    /**
     * set max memory for PatternMatch, e.g. 100 means 100M
     * @param mem memory size
     */
    public RegexPatternEngine(int mem) {
        matcher = new PatternMatch(mem);
        id2info = new HashMap<>();
    }
    
    @Override
    public List<ExtractInfo> extract(String text) {
        String[] matchIDs = matcher.matchIDs(text);
        if(matchIDs==null) {
            return null;
        }
        List<ExtractInfo> infos = new ArrayList<>();
        for(String id : matchIDs) {
            PatternInfo patInfo = id2info.get(id);
            infos.add(PatternUtil.extractInfoViaRegexWithTags(patInfo, text));
        }
        return infos;
    }

    @Override
    public void addPatterns(Iterator<PatternInfo> patInfoIter) {
        while(patInfoIter.hasNext()) {
            PatternInfo patInfo = patInfoIter.next();
            id2info.put(patInfo.getPatId(), patInfo);
            matcher.addSinglePattern(patInfo);
        }
    }

    @Override
    public void compile() {
        matcher.compile();
    }
    
}
