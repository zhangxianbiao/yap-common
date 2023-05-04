package com.yap.alg.greed;

import java.util.Arrays;

/**
 * 给一个正数的的数组，返回其中可以组成三角形的最大的周长
 * 提示：任意两边之和大于第三边，任意两边之差小于第三边
 * 三角形三个边： a,b,c，假设c为最长边，满足 a+b>c c>a c>b
 */
public class TriangleMaxPerimeter {

    public static int getTriangleMaxPerimeter(int[] nums){
        Arrays.sort(nums);
        for (int i = nums.length-1; i >= 2; i--){
            if (nums[i-2] + nums[i-1] > nums[i]){
                return nums[i-2] + nums[i-1] + nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,4,5,3,7,8};
        int max = getTriangleMaxPerimeter(nums);
        System.out.println(max);
    }
}
