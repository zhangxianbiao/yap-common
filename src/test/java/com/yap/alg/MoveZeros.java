package com.yap.alg;

import java.util.Arrays;
import java.util.List;

/**
 * LC 283 移动零
 * 给定一个数组nums，编写一个函数将所有的0移动到数组的尾部，同时保留非零元素的顺序
 * 思路： 双指针
 */
public class MoveZeros {

    public static void moveZeros(int[] nums){
        if (nums == null){
            return;
        }
        int j = 0;
        for (int i = 0; i< nums.length; i++){
            if (nums[i] != 0){
                nums[j++] = nums[i];
            }
        }
        //剩下的都是0了，填充0
        for (int i = j; i< nums.length; i++){
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeros(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
