package org.yqm.nlp.filter;

/**
 * interface for words filter
 * @author huangzixuan
 */
public interface IWordsFilter {
    /**
     * check whether filt given word 
     * @param word
     * @param label  label could be null if there is no label to give
     * @return boolean; true means should filt, false means should not.
     */
    public abstract boolean filt(String word, String label);
    
    /**
     * get the importance score of a word
     * @param word
     * @param label  label could be null if there is no label to give
     * @return double   the score of given word 
     */
    public abstract double getScore(String word, String label);
}
