package com.yaoh.JavaDemo.str;

/**
 * @author yaoh
 * @date 2020-03-15 17:08
 * @description TODO
 */
public class StrTest {

    public static void main(String[] args) {

        String s1 = "s1";
        System.out.println(s1.hashCode());
        s1 = "s2";
        System.out.println(s1.hashCode());
        s1 = "s3";
        System.out.println(s1.hashCode());

        StringBuffer buffer = new StringBuffer();
        buffer.append("1");
        System.out.println(buffer.hashCode());

        buffer.append("2");
        System.out.println(buffer.hashCode());


    }

}
