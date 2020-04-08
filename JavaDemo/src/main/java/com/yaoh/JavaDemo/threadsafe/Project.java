package com.yaoh.JavaDemo.threadsafe;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yaoh
 * @date 2020/4/3 10:01 AM
 * @description TODO
 */
public class Project {

    private Map<String, String> mData = new HashMap<>();

    public Map<String, String> getData() {
        return mData;
    }

    private int index;

    private ReentrantLock lock = new ReentrantLock();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    class MyTask implements Runnable {

        @Override
        public void run() {
            readWriteLock.readLock().lock();
            System.out.println("start read ==================> index:" + index);
            readWriteLock.readLock().unlock();

            readWriteLock.writeLock().lock();
            index++;
            System.out.println("start write ==================> index: " + index);
            System.out.println("end write ==================> index: " + index);
            readWriteLock.writeLock().unlock();

//            lock.unlock();
//            for (int i = 0; i < 10; i++) {
//                mData.put("key" + index + "," + i, "value" + i);
//            }
//            System.out.println("get ==================> index: " + index + " value: " + mData.get(index));
//            System.out.println("end ==================> index: " + index);
        }
    }

    public void testThread() {
        for (int i = 0; i < 100; i++) {
            new Thread(new MyTask()).start();
        }
    }
}
