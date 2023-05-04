package com.yap.alg.dp;

/**
 * NOT UNDERSTAND
 * HOT
 * LC 53 最大子序和
 */
public class MaxSubArray {

    public static int maxSubArray(int[] nums){
        int[] dp = new int[nums.length];

        dp[0] = nums[0];

        int result = dp[0];
        for (int i = 1; i< nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            if (dp[i] > result){
                result = dp[i];
            }
        }
        return result;
    }

    public static int maxSubArrayOpt(int[] nums){
        int result = nums[0];
        int pre = result;
        for (int i = 1; i< nums.length; i ++){
            pre = Math.max(pre + nums[i], nums[i]);
            if (pre > result){
                result = pre;
            }
        }
        return result;
    }


    public static void main(String[] args) {

    }

}
