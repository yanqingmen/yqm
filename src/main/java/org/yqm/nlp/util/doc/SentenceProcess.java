/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.util.doc;

/**
 * utils for sentence process
 * @author huangzixuan
 */
public class SentenceProcess {
    static String[] puncs = new String[] {"。","？","！","\\?","\\!"};
//    split doc to sentences via punctuation
    public static String[] split2Sents(String doc){
        String split_reg = "";
        for(String punc : puncs){
            if(!split_reg.isEmpty()){
                split_reg+="|";
            }
            split_reg += punc;
        }
        
        String[] sents = doc.split(split_reg);
        
        //add miss punc to each sent
        int index=0;
        for(int i=0; i<sents.length; i++) {
            String sent = sents[i];
            index = doc.indexOf(sent, index) + sent.length();
            if(index<doc.length()){
                sent += doc.substring(index, index+1);
            }
            sents[i] = sent;
        }
        return sents;
    }
    
    
    public String[] splitTaggedDoc2Sents(String doc) {
        String[] sents = null;
        return sents;
    }
}
