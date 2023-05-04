package com.yap.algo.leetcode;

/**
 * 有序数组，返回不重复元素的数量
 */
public class UniqueArrayNum {

    public static int uniqueArrayNum(int[] arr){
        int i = 0;
        for (int j = 1; j < arr.length; j++){
            // j快指针
            if (arr[i] != arr[j]){
                i++;
                arr[i] = arr[j];
            }
        }
        return i+1;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,3,4,4,5};
        int uniqueNum = uniqueArrayNum(arr);
        System.out.println(uniqueNum);
    }
}
