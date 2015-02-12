package cn.com.cninfo.nlp.data.reader;

import cn.com.cninfo.nlp.types.Instance;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * read data from single file
 * data format:
 * "data" + "separator" + "label"
 * @author huangzixuan
 */
public class SingleFileReader extends DataReader{
    private static final Log logger = LogFactory.getLog(SingleFileReader.class);
    
    String content = null;
    BufferedReader reader;
    int line;
    /**
     * default separator: tab  
     */
    String sep = "\t";
    boolean isSep = true;
    
    /**
     * read data with default separator
     * @param file 
     */
    public SingleFileReader(String file){
        init(file);
    }
    
    /**
     * @param file
     * @param sepFlag  if sepFlag==false, will read data without seperate line 
     */
    public SingleFileReader(String file, boolean sepFlag){
        init(file);
        isSep = sepFlag;
    }
    
    /**
     * read data with given separator
     * @param file
     * @param sep 
     */
    public SingleFileReader(String file, String sep){
        init(file);
        this.sep = sep;
    }
    
    private void init(String file){
        try{
            File f = new File(file);
            FileInputStream in = new FileInputStream(f);
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            logger.error(ex);
        }
        line = 0;
    }

    @Override
    public boolean hasNext() {
        try{
            while(true){
                content = reader.readLine();
                line++;
                if(content==null){
                    reader.close();
                    return false;
                }
                // skip empty line
                if(content.trim().length()>0){
                    break;
                }
            }
        } catch (IOException ex) {
            logger.error(ex);
            return false;
        }
        return true;
    }

    @Override
    public Instance next() {
        if(isSep==false){
            return new Instance(content);
        }
        String data;
        String label;
        int id = content.indexOf(sep);
        if(id==-1){
            logger.error("invalid format line: ");
            logger.error(line + "line: " + content);
            return null;
        }
        data = content.substring(0,id);
        label = content.substring(id+sep.length()).trim();
        return new Instance(data, label);
    }
}