package com.yaoh.JavaDemo.hashcode;

/**
 * @author yaoh
 * @date 2020-03-28 15:47
 * @description TODO
 */
public class Student {

    private String name;

//    @Override
//    public String toString() {
//        return "Student{" +
//                "name='" + name + '\'' +
//                '}';
//    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
//        System.out.println("this.hashCode(): " + this.hashCode() + " o.hashCode(): " + o.hashCode());
//        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
//        return 1000000;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
