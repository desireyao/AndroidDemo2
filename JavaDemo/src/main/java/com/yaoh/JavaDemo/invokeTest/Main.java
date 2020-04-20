package com.yaoh.JavaDemo.invokeTest;

import java.lang.reflect.Method;

/**
 * Created by yaoh on 2019/2/25
 */
public class Main {

    public static void main(String[] args) {
        testInvoke();
    }


    private static void testInvoke() {
        Model model = new Model();
        Class mClass = model.getClass();
        System.out.println("model class ---> " + model.getClass());

        Method[] methods = mClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("method222 ---> " + method.toString());
            Class[] parameterTypes = method.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.println("parameterType.getName()111===> " + parameterType.getName());
                if (parameterType == String.class) {
                    System.out.println("parameterType.getName() == String: " + parameterType.getName());
                }
            }

            try {
                Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
                if (subscribeAnnotation != null) {
                    if (method.getParameterCount() == 1) {
                        method.invoke(model, "haha111");
                    } else if (method.getParameterCount() == 2) {
                        method.invoke(model, "haha", 100);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
