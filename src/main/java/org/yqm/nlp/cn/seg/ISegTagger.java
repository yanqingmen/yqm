package org.yqm.nlp.cn.seg;

import java.util.List;

/*
 * Interface for word segment
 * author: huangzx
 * version: 1.0
 */

public interface ISegTagger {
    public abstract List<String> seg2List(String text);
    public abstract String seg2String(String text);
    public abstract String[] seg2Array(String text);
}
