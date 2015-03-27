package org.yqm.nlp.cn.pos;

import java.util.List;

/*
 * Interface for word pos
 * author: huangzx
 * version: 1.0
 */

public interface IPosTagger {
    public abstract List<String[]> tag2List(String text);
    public abstract String tag2String(String text);
    public abstract String[][] tag2Array(String text);
}
