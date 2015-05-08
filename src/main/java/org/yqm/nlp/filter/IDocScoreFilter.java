/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.filter;

import java.util.List;

/**
 * 
 * @author huangzixuan
 */
public interface IDocScoreFilter {
    public abstract double getScore(String text);
    public abstract double getScore(List<String> words);
}
