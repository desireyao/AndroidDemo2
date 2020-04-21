package com.yaoh.JavaDemo.algo.bubbling;

/**
 * @author yaoh
 * @date 2020/4/20 11:32
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        int array[] = new int[]{5, 4, 3, 2, 1};
        bubbleSort(array);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    public static void bubbleSort(int[] a) {
        int i, j;
        for (i = 0; i < a.length; i++) {//表示n次排序过程。
            for (j = i; j < a.length - i - 1; j++) {
                if (a[j + 1] > a[j]) {
                    int temp;
                    temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }


}
