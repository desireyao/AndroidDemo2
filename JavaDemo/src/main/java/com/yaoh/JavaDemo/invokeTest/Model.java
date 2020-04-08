package com.yaoh.JavaDemo.invokeTest;

import java.lang.reflect.Method;

/**
 * Created by yaoh on 2019/2/25
 */
public class Model {

    public void test() {
        Model model = new Model();
        Class mClass = model.getClass();
        System.out.println("model class ---> " + model.getClass());

        Method[] methods = mClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method-------> " + method.toString());
            try {
                Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
                if(subscribeAnnotation != null){
                    method.invoke(this, "haha", 100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe
    public void onEvent(String string, Integer num) {
        System.out.println("onEvent--------> string：" + string + " num：" + num);
    }

}
