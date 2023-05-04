package com.yap.alg;

import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * NOT UNDERSTAND
 * LC 448 找到所有数组中消失的数字
 * 给定一个包含n个整数的数组nums，其中nums[i]在区间[1,n]内，
 * 找到所有在[1,n]范围内没有出现的数字，并以数组的形式返回
 *
 */
public class FindDisappearNum {

    public static List<Integer> findDisappearNums(int[] nums){
        int n = nums.length;
        for (int num : nums){
            // 对n取模，还原其并本来的值
            int x = (num - 1)%n;
            // 这里的x是下标，第x个元素加上n
            nums[x] += n;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i<n; i++){
            if (nums[i] <= n){
                // 这里加进去的是i+1
                result.add(i+1);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1}; // -> [5,6]
        val disappearNums = findDisappearNums(nums);
        System.out.println(disappearNums);
    }

}
