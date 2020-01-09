package com.yaoh.JavaDemo.interfaces;

/**
 * Created by yaoh on 2019/3/12
 */
public interface ICoder {

    public void coding();

    default String name(){
        return "coder";
    }

}
