package com.yaoh.JavaDemo.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by yaoh on 2019/5/7
 */
public class TimeoutTaskUtil {

    /**
     * 执行一个有时间限制的任务
     *
     * @param task 待执行的任务
     * @param time 超时时间(单位: 毫秒)
     * @return
     */
    public static Boolean execute(Callable<Boolean> task, int time) {
        Boolean result = Boolean.FALSE;
        ExecutorService threadPool = Executors.newCachedThreadPool();
        System.out.println("threadName111: " + Thread.currentThread().getName());
        try {
            Future<Boolean> future = threadPool.submit(task);
            result = future.get(time, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            result = Boolean.FALSE;
            e.printStackTrace();
        } finally {
            threadPool.shutdownNow();
        }
        System.out.println("threadName222: " + Thread.currentThread().getName());


        return result;
    }

    static class MyTimeoutTask implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            for (int i = 0; i < 3; i++) {
                System.out.println("i = " + i + " threadName: " + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
            return true;
        }
    }


    public static void main(String[] args) {
        boolean result = execute(new MyTimeoutTask(), 1000);
        System.out.print("result---> " + result);

    }


}
