package com.yaoh.JavaDemo.invokeTest;

/**
 * Created by yaoh on 2019/2/25
 */
public class Model {


    @Subscribe
    public void test1(String s) {
        System.out.println("test1--------> string" + s);
    }

    public void test2() {

    }

    @Subscribe
    public void onEvent(String string, Integer num) {
        System.out.println("onEvent--------> string：" + string + " num：" + num);
    }

}
