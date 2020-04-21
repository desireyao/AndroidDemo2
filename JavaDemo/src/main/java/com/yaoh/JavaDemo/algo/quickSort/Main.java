package com.yaoh.JavaDemo.algo.quickSort;

import java.awt.DisplayMode;
import java.util.Collections;

/**
 * @author yaoh
 * @date 2020/4/6 4:42 PM
 * @description 快速排序是种不稳定的排序 时间复杂度为nlog2N
 */
public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{23, 46, 0, 8, 11, 18};
        quickSort2(array, 0, array.length - 1);

        for (int num : array) {
            System.out.println(num + ",");
        }
    }

    private static void quickSort2(int array[], int begin, int end) {
        if (end - begin <= 0) return;
        int x = array[begin];
        int low = begin;
        int high = end;
        boolean direction = true;

        L1:
        while (low < high) {
            if (direction) {
                for (int i = high; i > low; i--) {
                    if (array[i] <= x) {
                        array[low] = array[i];
                        low++;
                        high = i;
                        direction = !direction;
                        continue L1;
                    }
                }
                high = low;
            } else {
                for (int i = low; i < high; i++) {
                    if (array[i] >= x) {
                        array[high] = array[i];
                        high--;
                        low = i;
                        direction = !direction;
                        continue L1;
                    }
                }
                low = high;
            }
        }
        array[low] = x;
        //前半部分
        quickSort(array, begin, low - 1);
        // 后半部分
        quickSort(array, low + 1, end);
    }

    /**
     * @param array
     * @param begin
     * @param end
     */
    private static void quickSort(int[] array, int begin, int end) {
        if (end - begin <= 0) return;
        int x = array[begin];
        int low = begin;
        int high = end;

        boolean direction = true;

        L1:
        while (low < high) {
            if (direction) {
                for (int i = high; i > low; i--) {
                    if (array[i] <= x) {
                        array[low++] = array[i];
                        high = i;
                        direction = !direction;
                        continue L1;
                    }
                }
                high = low;
            } else {
                for (int i = low; i < high; i++) {
                    if (array[i] >= x) {
                        array[high--] = array[i];
                        low = i;
                        direction = !direction;
                        continue L1;
                    }
                }
                low = high;
            }
        }
        array[low] = x;

        //前半部分
        quickSort(array, begin, low - 1);
        // 后半部分
        quickSort(array, low + 1, end);
    }

}
