package cn.com.cninfo.nlp.cn.ner;

import java.util.List;

public interface INerTagger {
    public abstract List<String[]> tag2List(String text);
    public abstract String tag2String(String text);
    public abstract String[][] tag2Array(String text);
}
