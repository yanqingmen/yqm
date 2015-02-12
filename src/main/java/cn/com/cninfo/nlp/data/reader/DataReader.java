package cn.com.cninfo.nlp.data.reader;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import cn.com.cninfo.nlp.types.Instance;


/**
 * reader for data process
 */
public abstract class DataReader implements Iterator<Instance>{
    @Override
    public void remove (){
        throw new IllegalStateException ("This Iterator<Instance> does not support remove().");
    }
    
    public List<Instance> read(){
        List<Instance> instances = new ArrayList<>();
        while (hasNext()){
            Instance inst = next();
            if(inst!=null){
                instances.add(inst);
            }
        }
        return instances;
    }
}
