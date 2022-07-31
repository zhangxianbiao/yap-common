package com.yap.algo.leetcode;


import org.testng.annotations.Test;

import java.util.Arrays;

public class Sort {

    @Test
    public void test() {
        int[] arr = new int[] {9, 6, 8, 3, 7, 5, 4, 1, 2, 0, 10};

        int[] cloneArr1 = arr.clone();
        quickSortRecursion(cloneArr1, 0, cloneArr1.length - 1);
        System.out.println(Arrays.toString(cloneArr1));


    }

    /**
     * 快速排序
     * @param arr
     * @param start
     * @param end
     */
    public void quickSortRecursion(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotValue = arr[start];
        int i = start;
        int j = end;
        int temp;

        while (i < j) {
            // 右边当发现小于pivotValue的值时停止循环
            while (i < j && arr[j] > pivotValue) {
                j--;
            }
            // 左边当发现大于pivotValue的值时停止循环
            // 基准是start,必须<=才可以
            while (i < j && arr[i] <= pivotValue) {
                i++;
            }

            // 交换
            if (i < j) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }

        //这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[start] = arr[i];
        arr[i] = pivotValue;

        quickSortRecursion(arr, start,  j - 1);
        quickSortRecursion(arr, j + 1, end);
    }
}
