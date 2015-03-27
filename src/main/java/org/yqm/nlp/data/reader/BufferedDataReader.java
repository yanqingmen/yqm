/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.data.reader;

import org.yqm.nlp.types.Instance;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BufferedDataReader
 * @author huangzixuan
 */
public class BufferedDataReader extends DataReader {
    BlockingQueue<Instance> buffer;
    Producer producer;
    Instance inst;
    
    class Producer extends Thread {
        BlockingQueue<Instance> queue;
        DataReader reader;
        
        public Producer(DataReader reader, BlockingQueue queue) {
            this.reader = reader;
            this.queue = queue;
        }
        
        @Override
        public void run() {
            while(reader.hasNext()) {
                try {
                    queue.put(reader.next());
                } catch (InterruptedException ex) {
                    Logger.getLogger(BufferedDataReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public BufferedDataReader(DataReader reader) {
        this.buffer = new LinkedBlockingQueue<>();
        this.producer = new Producer(reader, buffer);
    }
    
    
    
    
    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Instance next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
