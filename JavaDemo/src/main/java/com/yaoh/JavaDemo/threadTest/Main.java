package com.yaoh.JavaDemo.threadTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yaoh on 2019/2/24
 */
public class Main {

//        static Map<Integer, String> mMap = new ConcurrentHashMap<>();
//    static Map<Integer, String> mMap = Collections.synchronizedMap(new HashMap<>());
    static Map<Integer, String> mMap = new HashMap<>();

    public static void main(String[] args) {
//        ThreadTest test = new ThreadTest();
//        test.test();

//        for (int i = 0; i < 100; i++) {
//            Task1 task = new Task1(i);
//            new Thread(task).start();
//
//            Task2 task2 = new Task2(i);
//            new Thread(task2).start();
//        }

//        for (int i = 0; i < 1000; i++) {
//            Task2 task = new Task2(i % 10);
//            new Thread(task).start();
//        }

        Task1 task1 = new Task1(0);
        Task2 task2 = new Task2(0);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n mMap: " + mMap + "\n mMap.size: " + mMap.size());

        for (int i = 0; i < 20; i++) {
            //如果key和value不同，说明在两个线程put的过程中出现异常。
            if (!String.valueOf(i).equals(mMap.get(i))) {
                System.out.println(String.valueOf(i) + ":" + mMap.get(String.valueOf(i)));
            }
        }

    }

    static class Task1 implements Runnable {
        private int num;

        public Task1(int num) {
            this.num = num;
        }

        @Override
        public void run() {
//            System.out.println("Task1 num111---> num: " + num);
//            mMap.put(num, String.valueOf(num));

            System.out.println("Task1 start--->");
            for (int i = 0; i < 20; i++) {
//                System.out.println("Task1 processing --->");
                mMap.put(i, String.valueOf(i));
            }
            System.out.println("Task1 end--->");

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Task1 num222---> " + num + " get:" + mMap.get(num));
//            System.out.println("Task1 num222--->put num: " + num);
//            mMap.put(num, String.valueOf(num));
        }
    }

    static class Task2 implements Runnable {
        private int num;

        public Task2(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("Task2 start--->");
            for (int i = 0; i < 20; i++) {
//                System.out.println("Task2 processing --->");
                mMap.put(i, String.valueOf(i));
//                mMap.get(i);
            }
            System.out.println("Task2 end--->");

//            System.out.println("Task2 num---> " + num);
//            mMap.put(num, String.valueOf(num + 1));
//            System.out.println("Task2 num---> " + num + "| " + mMap.get(num));
//            System.out.println("Task2 mMap.remove(num)111 ---> " + mMap.remove(num));
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Task2 num---> " + num + " get: " + mMap.get(num));
//            System.out.println("Task2 ---> num: " + num + " mMap.remove(num): " + mMap.remove(num));
        }
    }


}
