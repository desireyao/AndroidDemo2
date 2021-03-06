package com.yaoh.JavaDemo.algo.mergeSort;

/**
 * @author yaoh
 * @date 2020/4/20 13:13
 * @description TODO
 */
public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 6, 4, 3, 9, 8, 10, 7, 5};
        mergeSort(array, 0, array.length - 1);

        for (int i : array) {
            System.out.print(i + " ");
        }

    }

    public static void mergeSort(int array[], int left, int right) {
        if (left == right) {
            return;
        } else {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid + 1, right);
        }
    }

    //    0    4   7
    //    1  2  5  9 === 3  4  10  11
    public static void merge(int[] array, int left, int mid, int right) {
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        //生成数组
        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];
        //填充数据
        for (int i = left; i < mid; i++) {
            leftArray[i - left] = array[i];
        }
        for (int i = mid; i <= right; i++) {
            rightArray[i - mid] = array[i];
        }
        //合并
        int i = 0;
        int j = 0;
        int k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] < rightArray[j]) {
                array[k] = leftArray[i];
                k++;
                i++;
            } else {
                array[k] = rightArray[j];
                k++;
                j++;
            }
        }
        while (i < leftSize) {
            array[k] = leftArray[i];
            k++;
            i++;
        }
        while (j < rightSize) {
            array[k] = rightArray[j];
            k++;
            j++;
        }
    }

}
