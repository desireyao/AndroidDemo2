package com.yaoh.JavaDemo.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yaoh
 * @date 2020/4/8 11:07 AM
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        Iterator it = list.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            if (s.equals("8")) {
                it.remove();
            }
        }

        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            String s = (String) it2.next();
            System.out.println("====>" + s);
        }

        String data1 = "0";
        String data2 = "1";
        System.out.println("hashCode===>"
                + " data1: " + data1.hashCode()
                + " data2: " + data2.hashCode()
                + " identityHashCode: " + System.identityHashCode(data2));
    }
}
