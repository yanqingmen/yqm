/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.ie.pattern;

import org.yqm.ie.pattern.util.PatternInfo;
import org.yqm.nlp.data.reader.DataReader;
import org.yqm.nlp.types.Instance;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.google.re2fj.MultRegex;
/**
 * a fast pattern match engine based on google re2
 * @author hzx
 */
public class PatternMatch {
    private static final Log logger = LogFactory.getLog(PatternMatch.class);
    //muilt regex engine based match util
    MultRegex reg;
    //map from pattern to id
    Map<String, String> idMap;
    
    /**
     * set max memory for PatternMatch, e.g. 100 means 100M
     * @param mem memory size
     */
    public PatternMatch(int mem) {
        reg = new MultRegex(mem);
        idMap = new HashMap<>();
    }
    
    /**
     * add one pattern, this func will not recompile regex engine automatically
     * @param patInfo 
     */
    public void addSinglePattern(PatternInfo patInfo) {
        idMap.put(patInfo.getPatString(), patInfo.getPatId());
        reg.addPattern(patInfo.getPatString());
    }
    
    /**
     * recompile regex engine
     */
    public void compile() {
        reg.compile();
    }
    
    
    
    public int addPattern(Iterator<PatternInfo> patternIter) {
        int num = 0;
        while(patternIter.hasNext()) {
            PatternInfo patInfo = patternIter.next();
            idMap.put(patInfo.getPatString(), patInfo.getPatId());
            reg.addPattern(patInfo.getPatString());
            num++;
        }
        //comile reg engine after pattern added
        reg.compile();
        return num;
    }
    
    /**
     * add patterns 
     * @param reader DataReader
     * @return the number of patterns added
     */
    public int addPatterns(DataReader reader) {
        int num = 0;
        while(reader.hasNext()) {
            Instance inst = reader.next();
            try {
                PatternInfo patInfo = (PatternInfo) inst.getData();
                idMap.put(patInfo.getPatString(), patInfo.getPatId());
                reg.addPattern(patInfo.getPatString());
                num++;
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
        //comile reg engine after pattern added
        reg.compile();
        return num;
    }
    
    /**
     * get pattern ids which matches given text 
     * @param text
     * @return pattern ids
     */
    public String[] matchIDs(String text) {
        if(idMap.isEmpty()) {
            logger.warn("no pattern added, please add pattern before doing match");
            return null;
        }
        String[] patterns = reg.match(text);
        String[] ids;
        if(patterns!=null) {
            ids = new String[patterns.length];
        } else {
            ids = new String[0];
        }
        
        for(int i=0; i<ids.length; i++) {
            ids[i] = idMap.get(patterns[i]);
        }
        return ids;
    }
}
