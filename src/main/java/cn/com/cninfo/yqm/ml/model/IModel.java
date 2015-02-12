/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.cninfo.yqm.ml.model;

import cn.com.cninfo.nlp.types.Instance;

/**
 * Interface for model
 * @author huangzixuan
 */
public interface IModel {
    /**
     * @param x
     * @return 
     */
    public abstract Instance predict(Instance x);
}
