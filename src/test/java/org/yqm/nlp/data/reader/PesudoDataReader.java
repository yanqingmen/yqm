/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.data.reader;

import java.util.Iterator;
import org.yqm.nlp.types.Instance;

/**
 *
 * @author hzx
 */
public class PesudoDataReader extends DataReader {
    Iterator<Instance> iter;
    
    public PesudoDataReader(Iterator<Instance> iter) {
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Instance next() {
        return iter.next();
    }
    
}
