package com.yaoh.JavaDemo.mathTest;

/**
 * Created by yaoh on 2019/3/14
 */
public class Main {

    public static void main(String args[]) {
        double r = calculateDis(3,0,0,4);
        System.out.print("r: "+ r);

    }

    private static double calculateDis(int x0, int y0, int x1, int y1) {
        double  r = Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
        return r;
    }

}
