package org.yqm.nlp.data.reader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yqm.nlp.types.Instance;


public class BufferedDataReader extends DataReader{
    DataReader reader;
    BlockingQueue<Instance> queue;
    DataLoader loader;
    int bufferSize = 1000;
    
    public BufferedDataReader(DataReader reader) {
        init(reader);
    }

    public BufferedDataReader(DataReader reader, int bufferSize) {
        this.bufferSize = bufferSize;
        init(reader);
    }
    
    private void init(DataReader reader){
        this.reader = reader;
        this.queue = new LinkedBlockingQueue<>(this.bufferSize);
        this.loader = new DataLoader(this.reader, this.queue);
        new Thread(this.loader).start();
    }
    
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    @Override
    public Instance next() {
        return queue.poll();
    }
    
    
    private class DataLoader implements Runnable {
        DataReader reader;
        BlockingQueue<Instance> queue;
        
        public DataLoader(DataReader reader, BlockingQueue<Instance> queue) {
            this.reader = reader;
            this.queue = queue;
        }

        @Override
        public void run() {
            while(reader.hasNext()){
                try {
                    queue.put(reader.next());
                } catch (InterruptedException ex) {
                    Logger.getLogger(BufferedDataReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
