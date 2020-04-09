package com.yaoh.JavaDemo.hanoi;

/**
 * @author yaoh
 * @date 2020/4/9 10:25 AM
 * @description TODO
 */
public class Main {

    static long s = 0;

    public static void main(String args[]) {
        int n = 2;
        System.out.println("汉诺塔层数为" + n);
        System.out.println("移动方案为：");
        hanoi(n, 'a', 'b', 'c');
        System.out.println("需要移动次数：" + s);
    }

    static void hanoi(int n, char a, char b, char c) { //a为初始塔，b为中间塔，c为目标塔
        if (n == 1) {
            System.out.println("n=" + n + " " + a + "-->" + c);
            s++;
        } else {
            /*递归的调用*/
            hanoi(n - 1, a, c, b);
            System.out.println("n=" + n + " " + a + "-->" + c);
            hanoi(n - 1, b, a, c);
            s++;
        }
    }


}
