package com.yaoh.JavaDemo.threadTest;

/**
 * Created by yaoh on 2019/2/24
 */
public class ThreadTest {

    public void test() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run---------->111");
                long curtime = System.currentTimeMillis();
                while (System.currentTimeMillis() - curtime < 2000) {

                }
                System.out.println("run---------->222");
            }
        });

        thread.start();
        thread.interrupt();
    }


}
