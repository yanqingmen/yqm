/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.util.hash;

import org.yqm.nlp.types.Instance;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Index with simhash
 * @author huangzixuan
 */
public class SimHashIndex {
    private static final Log logger = LogFactory.getLog(SimHashIndex.class);
    
    SimHash simHash;
    Map<BitSet, Set<Integer>> hashIndex;
    List<String> textDocs;
    List<Long> docHashes;
    int defaultThd = 3;
    int indexBits = 16;
    
    public SimHashIndex() {
        init();
    }
    
    //set the maxDistance between docs we considered as similarity, default is 3
    public SimHashIndex(int maxDist) {
        if(maxDist<=16) {
            defaultThd = maxDist;
            indexBits = 64/(maxDist+1);
        }
        else {
            logger.warn("maxDist is too large for SimHashIndex, it will be set to default 3");
        }
        init();
    }
    
    private void init() {
        simHash = new SimHash();
        textDocs = new ArrayList<>();
        docHashes = new ArrayList<>();
        hashIndex = new HashMap<>();
    }
    
    public void createIndex(Iterator<Instance> docs) {
        while(docs.hasNext()) {
            addDoc2Index(docs.next());
        }
    }
    
    public Map<String, Integer> search(String doc) {
        return search(doc, defaultThd);
    }
    
    public Map<String, Integer> search(String doc, int thd) {
        if(thd > defaultThd) {
            logger.warn("given thd is larger than max thd, it will be set to max thd " + defaultThd);
            thd = defaultThd;
        }
        Set<Integer> docCandidates = new HashSet<>();
        long docHash = simHash.simhash64(doc);
        BitSet key = new BitSet(indexBits);
        
        int step = 0;
        for(int i=0; i<64; i++) {
            key.set(step, ((docHash >> i) & 1) == 1);
            if(step++ == indexBits) {
                if(hashIndex.containsKey(key)){
                    docCandidates.addAll(hashIndex.get(key));
                }
                step = 0;
                key = new BitSet(indexBits);
            }
        }
        
        Map<String, Integer> simDocs = new HashMap<>();
        for(Integer idx : docCandidates) {
            int dist = simHash.hammingDistance(docHash, docHashes.get(idx));
            if (dist <= thd) {
                simDocs.put(textDocs.get(idx), dist);
            }
        }
        return simDocs;
    } 
    
    public void addDoc2Index(Instance doc) {
        String newDoc = (String) doc.getData();
        addDoc2Index(newDoc);
    }
    
    public void addDoc2Index(String doc) {
        int idx = textDocs.size();
        textDocs.add(doc);
            
        long docHash = simHash.simhash64(doc);
        docHashes.add(docHash);
           
        BitSet key = new BitSet(indexBits);
        int step = 0;

        for (int i = 0; i < 64; ++i) {
            key.set(step, ((docHash >> i) & 1) == 1);
            if (step++ == indexBits) {
                /*
                 * a) Separates the hash in 12-bit keys. b) This value will
                 * be a key in hashIndex. c) hashIndex will contain sets of
                 * documents matching each key (12-bits).
                 */
                if (hashIndex.containsKey(key)) {
                    hashIndex.get(key).add(idx);
                } else {
                    HashSet<Integer> vector = new HashSet<>();
                    vector.add(idx);
                    hashIndex.put(key, vector);
                }
                step = 0;
                key = new BitSet(indexBits); // reset key holder.
            }
        }
    }
}
