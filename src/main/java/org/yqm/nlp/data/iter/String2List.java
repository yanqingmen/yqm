package org.yqm.nlp.data.iter;

import org.yqm.nlp.cn.seg.ISegTagger;
import org.yqm.nlp.types.Instance;
import java.util.Iterator;
import java.util.List;

/**
 * iterator for process String 2 List
 * @author huangzixuan
 */
public class String2List implements Iterator<Instance>{
    Iterator<Instance> stringIter;
    ISegTagger segger;
    
    /** 
     * @param stringIter an iterator of Instance, the data type of Instance should be String
     * @param segger an segger to process String and return a List
     */
    public String2List(Iterator<Instance> stringIter, ISegTagger segger) {
        this.stringIter = stringIter;
        this.segger = segger;
    }

    @Override
    public boolean hasNext() {
        return stringIter.hasNext();
    }

    @Override
    public Instance next() {
        Instance inst = stringIter.next();
        String data = (String) inst.getData();
        List<String> words = segger.seg2List(data);
        inst.setData(words);
        return inst;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
