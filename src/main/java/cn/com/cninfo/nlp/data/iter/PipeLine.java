/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.cninfo.nlp.data.iter;

import cn.com.cninfo.nlp.data.pipe.Pipe;
import cn.com.cninfo.nlp.data.reader.DataReader;
import cn.com.cninfo.nlp.types.Instance;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huangzixuan
 */
public class PipeLine extends DataReader {
    Pipe pipes;
    DataReader reader;
    
    public PipeLine(DataReader reader, Pipe pipes) {
        this.reader = reader;
        this.pipes = pipes;
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }

    @Override
    public Instance next() {
        Instance inst = reader.next();
        try {
            pipes.addThruPipe(inst);
        } catch (Exception ex) {
            Logger.getLogger(PipeLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inst;
    }
    
}
