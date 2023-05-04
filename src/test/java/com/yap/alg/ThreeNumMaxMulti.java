package com.yap.alg;

public class ThreeNumMaxMulti {

    /**
     * 1.都是正数 取最大的三个数
     * 2.都是负数 取最大的三个数
     * 3.有正有负 取最大的三个正数 or 最小的两个数(负数) * 最大的一个数(正数)（只有一个负数时，如果只有三个数，只能取，大于三个数，肯定取三个正数）
     * @param nums
     * @return
     */
    public static int maxMulti(int[] nums){
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int num : nums){
            if (num < min1){
                min2 = min1;
                min1 = num;
            }else if(num < min2){
                min2 = num;
            }

            if (num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if(num > max2){
                max3 = max2;
                max2 = num;
            }else if(num > max3){
                max3 = num;
            }
        }
        return Math.max(max1*max2*max3, min1*min2*max1);
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{-9, -4, -2, 0, 2, 4, 1, 6};
        int i = maxMulti(arr1);
        System.out.println(i);
    }
}
