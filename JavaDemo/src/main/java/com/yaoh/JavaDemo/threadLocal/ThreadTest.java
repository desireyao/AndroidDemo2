package com.yaoh.JavaDemo.threadLocal;

/**
 * Created by yaoh on 2019/2/23
 */
public class ThreadTest {

    public void testThreadLocal(){
        Thread t1 = new Thread() {
//            ThreadLocal<String> mStringThreadLocal = new ThreadLocal<>();
            @Override
            public void run() {
                super.run();
//                mThreadLocal.set("t1============> ");
                System.out.println("t1---->" + mThreadLocal.get());
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
//                mThreadLocal.set("t2");
                System.out.println("t2---->" + mThreadLocal.get());
            }
        };
        t2.start();

        Thread t3 = new Thread() {
            @Override
            public void run() {
                super.run();
//                mThreadLocal.set("t2");
                System.out.println("t3---->" + mThreadLocal.get());
            }
        };
        t3.start();
    }


    ThreadLocal<String> mThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

}
