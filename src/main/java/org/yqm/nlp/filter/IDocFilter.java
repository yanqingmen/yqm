/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.nlp.filter;

/**
 * a document filter
 * @author huangzixuan
 */
public interface IDocFilter {
    public abstract boolean filt(String text);
}
