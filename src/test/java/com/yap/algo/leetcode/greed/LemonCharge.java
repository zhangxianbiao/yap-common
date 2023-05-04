package com.yap.algo.leetcode.greed;

/**
 * 一杯柠檬水5元，顾客购买柠檬水，可以支付5、10、20元，
 * 必须给顾客正确的找零
 */
public class LemonCharge {


    public static boolean canLemonCharge(int[] nums){

        int num5 = 0;
        int num10 = 0;
        for (int bill : nums){
            if (bill == 5){
                num5++;
            }else if (bill==10){
                if (num5 == 0){
                    return false;
                }
                num5--;
                num10++;
            }else {
                if (num10>0&& num5>0){
                    num5--;
                    num10--;
                }else if (num5>=3){
                    num5-=3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,5,10,10,5};
        boolean b = canLemonCharge(nums);
        System.out.println(b);
    }

}
