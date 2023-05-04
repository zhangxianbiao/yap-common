package com.yap.alg.greed;

import java.util.*;

/**
 * 给定两个大小相等的数组， A相对B的优势满足A[i] > B[i]
 * 返回A的任意排列，使其对B的优势最大化
 */
public class TianJiSaiMa {
    public static int[] advantageCount(int[] A, int[] B){

        // AB 都需要排序，防止每次都要遍历
        // B的索引位置需要记住，都需要一个副本
        int[] sortedA = A.clone();
        Arrays.sort(sortedA);

        int[] sortedB = B.clone();
        Arrays.sort(sortedB);

        Map<Integer, Deque<Integer>> assigned = new HashMap<>();

        // 这种方式B不能重复
        for (int b: B){
            assigned.put(b, new LinkedList<>());
        }

        Deque<Integer> remaining = new LinkedList<>();

        int j = 0;
        for (int a: sortedA){
            // 找到一个比a大的最小索引的b元素
            if (a > sortedB[j]){
                // 把配对的A放在对用的B后面
                assigned.get(sortedB[j]).add(a);
                j++;
            }else {
                // 不能大于最小的B，那么只能舍弃
                remaining.add(a);
            }
        }

        int[] ans = new int[B.length];
        for (int i = 0; i<B.length; i++){
            if (assigned.get(B[i]).size() > 0){
                ans[i] = assigned.get(B[i]).removeLast();
            }else {
                ans[i] = remaining.removeLast();
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
