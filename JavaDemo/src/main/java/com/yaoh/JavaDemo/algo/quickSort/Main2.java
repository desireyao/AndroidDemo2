package com.yaoh.JavaDemo.algo.quickSort;

/**
 * @author yaoh
 * @date 2020/4/6 4:42 PM
 * @description TODO
 */
public class Main2 {

    public static void main(String[] args) {
        int[] array = new int[]{7, 5, 56, 4, 3, 2, 100, 44, 1};
        quickSort(array, 0, array.length - 1);

        for (int num : array) {
            System.out.println(num + ",");
        }

    }

    /**
     * 时间复杂度 O(nlogN)
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
        quickSort(array, begin, low - 1);
        quickSort(array, low + 1, end);

    }

}
