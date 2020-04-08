package com.yaoh.JavaDemo.algo.binarysearch;

/**
 * @author yaoh
 * @date 2020/4/6 4:03 PM
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {

        int[] array = new int[]{1, 5, 6, 7, 8, 10, 23, 55,63,77, 100};
        int target = 77;
        System.out.println(binarySearch(array, 0, array.length, target));
    }


    /**
     * 时间复杂度 logN
     * @param array
     * @param fromIndex
     * @param toIndex
     * @param target
     * @return
     */
    private static int binarySearch(int[] array, int fromIndex, int toIndex, int target) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midVal = array[mid];
            if (target > midVal) {
                low = mid + 1;
            } else if (target < midVal) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
