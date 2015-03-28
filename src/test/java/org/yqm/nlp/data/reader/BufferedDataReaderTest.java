/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.data.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Test;
import org.yqm.nlp.types.Instance;
/**
 *
 * @author hzx
 */
public class BufferedDataReaderTest extends TestCase  {
    @Test
    public void testBufferedDataReader() {
        String[] test = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
        List<Instance> instances = new ArrayList<>();
        for(String item : test) {
            instances.add(new Instance(item));
        }
        
        DataReader reader = new PesudoDataReader(instances.iterator());
        
        BufferedDataReader bfReader = new BufferedDataReader(reader, 5);
        try {
            Thread.sleep(10L);
        } catch (InterruptedException ex) {
            Logger.getLogger(BufferedDataReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int index = 0;
        while(bfReader.hasNext()) {
            Instance  inst = bfReader.next();
            System.out.println(inst.getData() + "\t" + instances.get(index).getData());
            index++;
        }
    }
}
