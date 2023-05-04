package com.yap.alg.stackandqueue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC 394 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 */
public class DecodeString {

    public static String decodeString(String str){
        // 使用双端队列来模拟stack
        Deque<String> stack = new LinkedList<>();
        int index = 0;

        while (index < str.length()){
            char curChar = str.charAt(index);
            if (Character.isDigit(curChar)){
                // 得到完整的数字，遇到不是数字就停止
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(str.charAt(index))){
                    sb.append(str.charAt(index));
                    index ++;
                }
                stack.addLast(sb.toString());
            }else if (Character.isLetter(curChar) || curChar == '['){
                // 处理字符和左括号'['
                stack.addLast(String.valueOf(str.charAt(index)));
                index ++;
            }else {
                // 说明遇见了']'，处理和其匹配的'['之间的字符
                index ++;

                // 使用一个List，找到'['之前的字符串
                List<String> list = new LinkedList<>();
                while (!"[".equals(stack.peekLast())){
                    list.add(stack.removeLast());
                }
                //因为栈的特点，导致组合的字符串是原来的字符串的倒序，需要翻转一次
                Collections.reverse(list);

                //左括号'['出栈
                stack.removeLast();
                // 此时栈顶是当前的list对用的字符串应该出现的次数
                int repeatTimes = Integer.parseInt(stack.removeLast());
                StringBuilder sb = new StringBuilder();

                while (repeatTimes-- > 0){
                    sb.append(String.join("", list));
                }
                // 构造好的字符串入栈
                stack.addLast(sb.toString());
            }
        }
        return String.join("", stack);
    }

    public static void main(String[] args) {
        String test1 = "3[a]2[bc]";// -> aaabcbc
        String test2 = "3[a2[c]]";// -> accaccacc

        System.out.println(decodeString(test1));
        System.out.println(decodeString(test2));
    }
}
