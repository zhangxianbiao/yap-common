package com.yap.alg;

import java.util.HashMap;
import java.util.Map;

/**
 * 从数组中找到两个不重复的数，使得其和等于target
 */
public class TwoSums {

    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< nums.length; i++){
            int other = target - nums[i];
            if (map.containsKey(other)){
                return new int[]{map.get(other), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     *
     * @param nums 有序数组
     * @param target 等于目标值的两个数唯一
     * @return
     */
    public static int[] twoSumOrder(int[] nums, int target){

        int l = 0;
        int r = nums.length -1;
        // 不用等，因为不能重复
        while (l < r){
            int sum = nums[l] + nums[r];
            if (sum < target){
                l++;
            }else if (sum>target){
                r--;
            }else if(sum == target){
                return new int[]{l, r};
            }
        }

        return new int[]{-1, -1};
    }



    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 3, 7, 9, 8};
        int[] ints = twoSum(nums, 8);
        System.out.println(ints[0] + " " + ints[1]);


        int[] numOrder = new int[]{1, 2, 5, 7, 9, 15};
        int[] ints2 = twoSumOrder(numOrder, 14);
        System.out.println(ints2[0] + " " + ints2[1]);
    }
}
