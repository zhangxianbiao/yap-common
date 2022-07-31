package com.yap.algo.leetcode;

import org.testng.annotations.Test;

public class BinarySearch {

    @Test
    public void test() {
        int[] arr = new int[] {0, 1, 2, 3, 4, 5, 5, 5, 6, 6, 7, 8, 9, 10, 11};

        int binarySearchMid = binarySearchMid(arr, 5);
        System.out.println(binarySearchMid);

        int binarySearchLeft = binarySearchLeft(arr, 5);
        System.out.println(binarySearchLeft);
    }

    /**
     * 找到中间的数的索引
     * @param arr
     * @param num
     * @return
     */
    public int binarySearchMid(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;

        int mid = 0;
        while (left <= right) {
            mid = left + (right - left)/2;
            if (num > arr[mid]) {
                left = mid + 1;
            } else if (num < arr[mid]) {
                right = mid - 1;
            } else if(num == arr[mid]) {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 返回左边界
     * @param arr
     * @param num
     * @return
     */
    public int binarySearchLeft(int[] arr, int num) {
        int left = 0;
        int right = arr.length - 1;

        int mid = 0;
        while (left <= right) {
            mid = left + (right - left)/2;
            if (num > arr[mid]) {
                left = mid + 1;
            } else if (num < arr[mid]) {
                right = mid - 1;
            } else if(num == arr[mid]) {
                // 继续搜索边界
                right = mid - 1;
            }
        }
        // 兜底判断
        if (left >= arr.length || arr[left] != num) {
            return -1;
        }
        return left;
    }
}
