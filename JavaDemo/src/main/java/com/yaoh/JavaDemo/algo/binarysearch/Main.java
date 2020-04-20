package com.yaoh.JavaDemo.algo.binarysearch;

/**
 * @author yaoh
 * @date 2020/4/6 4:03 PM
 * @description 二分法查找时间复杂度LogN, 前提需要先排好序
 */
public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{1, 5, 6, 7, 8, 10, 23, 55, 63, 77, 100};
        int target = 55;
        int targetIndex = binarySearch(array, 0, array.length, target);
        System.out.println(" targetIndex==========> " + targetIndex);
    }

    /**
     * 二分法查找
     *
     * @param array
     * @param fromIndex
     * @param endIndex
     * @param target
     * @return
     */
    private static int binarySearch(int[] array, int fromIndex, int endIndex, int target) {
        int low = fromIndex;
        int high = endIndex - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midVal = array[mid];
            if (target < midVal) {
                high = mid - 1;
            } else if (target > midVal) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


}
