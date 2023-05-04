package com.yap.algo.leetcode;

import java.util.Arrays;

public class MergeTwoSortArray {

    public static int[] getMergeTwoSortArray(int[] arr1, int[] arr2){
        if (arr1.length == 0 || arr2.length == 0){
            return null;
        }

        int[] arr = new int[arr1.length + arr2.length];
        int i = 0, j = 0;
        int index = 0;
        while (i < arr1.length && j < arr2.length){
            if (arr1[i] > arr2[j]){
                arr[index] = arr2[j];
                j++;
            }else {
                arr[index] = arr1[i];
                i++;
            }
            index ++;
        }

        while (i < arr1.length){
            arr[index] = arr1[i];
            index++;
            i++;
        }
        while (j < arr2.length){
            arr[index] = arr2[j];
            index++;
            j++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3,5,7,9,80,99};
        int[] arr2 = new int[]{2,4,6,8,10};


        int[] arr = getMergeTwoSortArray(arr1, arr2);
        //Arrays.asList(arr).stream().forEach(System.out::println);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
