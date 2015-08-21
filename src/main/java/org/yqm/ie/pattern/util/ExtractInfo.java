/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.ie.pattern.util;

import java.util.List;

/**
 * extracted info from pattern engine
 * @author hzx
 */
public class ExtractInfo {
    //extracted
    public List<String> extractedPhrases;
    //tags for extracted phrases
    public List<String> tags;
    //indexs for phrases in original text
    public List<int[]> indexs;
    //matched patterninfo
    public PatternInfo matchedPattern;
}
