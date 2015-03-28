/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.data.reader;

import org.yqm.nlp.data.reader.DataReader;
import org.yqm.nlp.types.Instance;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author huangzixuan
 */
public class StringDataReaderFactory {
    public static class StringDataReader extends DataReader {
        Iterator<String> iter;
        
        public StringDataReader(Iterator<String> iter) {
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Instance next() {
            Instance inst = new Instance(iter.next());
            return inst;
        }
    }
    
    public static DataReader create(List<String> stringList) {
        DataReader reader = new StringDataReader(stringList.iterator());
        return reader;
    }
}
