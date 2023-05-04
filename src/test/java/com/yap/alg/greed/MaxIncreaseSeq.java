package com.yap.alg.greed;

/**
 * 给一个未经排序的整数数组，找到最长且连续的递增子序列，返回子序列的长度
 */
public class MaxIncreaseSeq {
    public static int findMaxIncreaseSeq(int[] nums){
        int maxLength = 0;
        int start = 0;
        for (int i = 1;i<nums.length; i++){
            if (nums[i]<=nums[i-1]){
                // 没有继续增长
                start = i;
            }
            maxLength = Math.max(maxLength, i-start+1);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,2,4,3,4,5,6,1};
        int maxIncreaseSeq = findMaxIncreaseSeq(nums);
        System.out.println(maxIncreaseSeq);
    }
}
