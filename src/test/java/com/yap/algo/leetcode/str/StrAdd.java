package com.yap.algo.leetcode.str;

public class StrAdd {

    public static String strAdd(String a, String b){
        StringBuilder sb = new StringBuilder();
        int jinWei = 0;

        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i>=0 || j >=0 || jinWei == 1){
            int num1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int num2 = j >= 0 ? b.charAt(j) - '0' : 0;

            int sum = (num1+num2 + jinWei)%10;
            jinWei = (num1 + num2 + jinWei) /10;
            sb.append(sum);

            i--;
            j--;
        }
//        if (jinWei ==1){
//            sb.append(jinWei);
//        }
        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        String a = "25";
        String b = "86";

        System.out.println(strAdd(a, b));//"111"
    }
}
