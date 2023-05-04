package com.yap.algo.leetcode.str;

import org.apache.commons.lang3.StringUtils;

/**
 * 方法
 * 1. BF (Brute Force)，暴力搜索，java中String的indexOf方法，时间复杂度O(m*n)
 * 2. RK Rabin-Carp, hash算法，时间复杂度O(n), 两位发明者Rabin Karp 的名字命名的,对暴力搜索的优化
 *      https://tsejx.github.io/data-structure-and-algorithms-guidebook/algorithms/search/rk/
 * 3. BM Boyer-Moore, 坏字符和好后缀规则，时间复杂度O(n/m)  最坏O(m*n) ，由两位发明者Boyer 和Moore 的名字命名的
 * 4. KMP Knuth，Morris和Pratt发明了这个算法，然后取它三个的首字母进行了命名，主要应用在 字符串匹配中
 *      https://tsejx.github.io/data-structure-and-algorithms-guidebook/algorithms/search/kmp
 *
 * 高效的算法BM和KMP都是需要空间作为代价的，特别是BM，任何一个字符串都需要至少64K内存，考虑到L1 Cache大小，cost更不可知。
 * JDK应该默认了不会使用String.indexOf查找过大的字符串，对于轻量级（通常不超过几十个字符），暴力用时也非常短。
 * 这也提示String.indexOf的客户端程序员应该对于自己使用的API有所了解，
 * 如果要进行过大的字符串查找，应该自己设计算法（取出两个字符串的value[]，然后实现BM或KMP）或调用第三方工具专门处理。
 */
public class StringSearch {

    /**
     * 暴力搜索
     * @param originStr
     * @param patternStr
     * @return
     */
    public static int getIndex(String originStr, String patternStr){
        if (StringUtils.isBlank(originStr) || StringUtils.isBlank(patternStr)){
            return -1;
        }

        // ABCABCDABF 10
        // ABF 3
        for (int i = 0; i <= originStr.length() - patternStr.length(); i++){
            int j = 0;
            for (j = 0; j < patternStr.length(); j++){
                if (originStr.charAt(i + j) != patternStr.charAt(j)){
                    break;
                }
            }
            if (j == patternStr.length()){
                return i;
            }
        }
        return -1;
    }


    public static int getIndexKMP(String originStr, String patternStr){
        if (StringUtils.isBlank(originStr) || StringUtils.isBlank(patternStr)){
            return -1;
        }

        final int[] nextArr = getNextArr(patternStr);

        int i =0, j = 0;
        while (i < originStr.length() && j<patternStr.length()){
            if (j == -1 || originStr.charAt(i) == patternStr.charAt(j)){
                i++;
                j++;
            }else {
                j = nextArr[j];
            }
        }
        if (j == patternStr.length()){
            return i - j;
        }else {
            return -1;
        }
    }

    public static int[] getNextArr(String pattern){
        int[] next = new int[pattern.length()];

        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < pattern.length()){
            if (j == -1){
                i++;
                j++;
            }else if (pattern.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
                next[i] = j;
            }else {
                j = next[j];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String ori = "ABCABCDABF";
        String pattern = "ABF";

        // indexOf
        final int index = ori.indexOf(pattern);
        System.out.println(index);

        // 暴力
        final int index2 = getIndex(ori, pattern);
        System.out.println(index2);

        final int index3 = getIndexKMP(ori, pattern);
        System.out.println(index3);
    }











}
