package com.yaoh.JavaDemo.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaoh
 * @date Create in 2019-10-30 13:14
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> mList1 = new ArrayList<>();
        mList1.add(1);
        mList1.add(2);
        mList1.add(3);
        System.out.println("mList1---> " + mList1);

        List<Integer> mList2 = new ArrayList<>();
        mList2.addAll(mList1);
        System.out.println("mList1---> " + mList1);


//        A a = new A();
        B b = new B();
        b.setName("haha");

        A a = b;
        B b2 = (B) a;
        b2.get();
        b2.get2();

    }


}
