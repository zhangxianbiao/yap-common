package com.yap.alg;

public class SqrtX {

    public static int sqrtUseBinarySearch(int x) {
        int l = 0, r = x, ret = -1;
        while (l <= r){
            int mid = l + (r-l)/2;
            if (mid * mid <= x){
                ret = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int x = 99;
        int i = sqrtUseBinarySearch(x);
        System.out.println(i);
    }
}
