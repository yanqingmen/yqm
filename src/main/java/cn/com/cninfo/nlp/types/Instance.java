package cn.com.cninfo.nlp.types;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * a single instance (x,y). x is data and y is target.
 * @author huangzixuan
 */
public class Instance implements Serializable{
    private static final long serialVersionUID = 201412241624L;
    
    Log logger = LogFactory.getLog(Instance.class);
    
    /**
     * data x
     */
    protected Object data;
    /**
     * label y
     */
    protected Object label;
    /**
     * source version of data x
     */
    private Object source;
    /**
     * temp data
     */
    private Object tempData;
    
    public Instance(){
    }
    
    public Instance(Object data){
        this.data = data;
    }
    
    public Instance(Object data, Object label){
        this.data = data;
        this.source = data;
        this.label = label;
    }
    
    public Object getLabel(){
        return label;
    }
    
    public void setLabel(Object label){
        this.label = label;
    }
    
    public Object getData(){
        return data;
    }
    
    public void setData(Object data){
        this.data = data;
    }
    
    public Object getSource(){
        return source;
    }
    
    public void setSource(Object source){
        this.source = source;
    }
    
    public void setTempData(Object tempData){
        this.tempData = tempData;
    }
    
    public Object getTempData(){
        return tempData;
    }
    
    public int length(){
        int ret = 0;
        if (data instanceof int[]){
            ret = 1;
        }else if (data instanceof int[][]){
            ret = ((int[][]) data).length;
        }else if (data instanceof int[][][]){
            ret = ((int[][][]) data)[0].length;
        }
        return ret;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(data.toString());
        return sb.toString();
    }
}
