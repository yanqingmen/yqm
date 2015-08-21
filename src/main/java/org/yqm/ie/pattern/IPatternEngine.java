/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.ie.pattern;

import org.yqm.ie.pattern.util.ExtractInfo;
import org.yqm.ie.pattern.util.PatternInfo;
import java.util.Iterator;
import java.util.List;

/**
 * Interface for pattern engines, extract matched infos
 * @author hzx
 */
public interface IPatternEngine {
    public void addPatterns(Iterator<PatternInfo> patInfoIter);
    public void compile();
    public abstract List<ExtractInfo> extract(String text);
}
