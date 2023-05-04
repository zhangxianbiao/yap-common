package com.yap.alg;

public class SubArrayMaxAvg {
    /**
     * 滑动窗口，双指针的特例
     * @param arr
     * @param k
     * @return
     */
    public static int getSubArrayMaxAvg(int[] arr, int k){
        int sum = 0;
        for (int i = 0; i<k; i ++){
            sum += arr[i];
        }
        int max = sum;
        for (int i = k; i<arr.length; i ++){
            sum = sum - arr[i - k] + arr[i];
            max = Math.max(sum, max);
        }
        return (int)max/k;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,-5,6,7,11};
        int subArrayMaxAvg = getSubArrayMaxAvg(arr, 3);
        System.out.println(subArrayMaxAvg);
    }
}
