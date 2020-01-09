package com.yaoh.JavaDemo.data;

/**
 * @author yaoh
 * @date Create in 2019-10-30 15:30
 * @description TODO
 */
public class B extends A {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void get() {
        System.out.println("B get--------> name: " +name);
    }

    public void get2() {
        System.out.println("B get2-------->name: " + name);
    }

}
