package com.yap.alg;

import java.util.Arrays;

/**
 * 寻找一个下标，左右两边的元素和相等
 */
public class ArrayPivotIndex {

    public static int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        int sumLeft = 0;
        for (int i = 0; i< nums.length; i++){
            sumLeft += nums[i];
            if (sum == sumLeft){
                return i;
            }
            sum = sum - nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arrs = new int[]{2,3,8,3,6,7};
        int i = pivotIndex(arrs);
        System.out.println(i);
    }
}
