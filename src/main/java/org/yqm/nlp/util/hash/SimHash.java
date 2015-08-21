/*
* The MIT License (MIT) 
* 
* Copyright (c) 2012 Cheng Zhang 
* 
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of 
* this software and associated documentation files (the "Software"), to deal in 
* the Software without restriction, including without limitation the rights to 
* use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of 
* the Software, and to permit persons to whom the Software is furnished to do so, 
* subject to the following conditions: 
* 
* The above copyright notice and this permission notice shall be included in all 
* copies or substantial portions of the Software. 
* 
*  
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
* FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
* COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
* IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
* CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
*/
package org.yqm.nlp.util.hash;

import org.yqm.nlp.cn.seg.ISegTagger;
import org.yqm.nlp.cn.seg.impl.CharNgramSegTagger;
import java.util.List;

/**
 * a simple simhash class
 * copy from https://github.com/sing1ee/simhash-java.git
 */
public class SimHash {
    ISegTagger segger;
    
    public SimHash(ISegTagger segger) {
        this.segger = segger;
    }
    
    public SimHash() {
        segger = new CharNgramSegTagger(2);
    }
    
    public int hammingDistance(int hash1, int hash2) {
        int i = hash1 ^ hash2;
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
    
    public int hammingDistance(long hash1, long hash2) {
        long i = hash1 ^ hash2;
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int) i & 0x7f;
    }
    
    public long simhash64(String doc) {
        int bitLen = 64;
        int[] bits = new int[bitLen];
        List<String> tokens = segger.seg2List(doc);
        for (String t : tokens) {
            long v = MurmurHash.hash64(t);
            for (int i = bitLen; i >= 1; --i) {
                if (((v >> (bitLen - i)) & 1) == 1)
                    ++bits[i - 1];
                else
                    --bits[i - 1];
            }
        }
        long hash = 0x0000000000000000;
        long one = 0x0000000000000001;
        for (int i = bitLen; i >= 1; --i) {
            if (bits[i - 1] > 1) {
                hash |= one;
            }
            one = one << 1;
        }
        return hash;
    }

    public long simhash32(String doc) {
        int bitLen = 32;
        int[] bits = new int[bitLen];
        List<String> tokens = segger.seg2List(doc);
        for (String t : tokens) {
            int v = MurmurHash.hash32(t);
            for (int i = bitLen; i >= 1; --i) {
                if (((v >> (bitLen - i)) & 1) == 1)
                    ++bits[i - 1];
                else
                    --bits[i - 1];
            }
        }
        int hash = 0x00000000;
        int one = 0x00000001;
        for (int i = bitLen; i >= 1; --i) {
            if (bits[i - 1] > 1) {
                hash |= one;
            }
            one = one << 1;
        }
        return hash;
    }
}
