package com.algorithms.normal;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * @author DUAN
 * @date 2019/12/20
 */
public class ValidBrackets {

    private boolean isValid(String s) {
        if (s == null || s.isEmpty()){
            return true;
        }
        int length = s.length();
        if (length < 2 || length % 2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            else {
                if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '('){
                        return false;
                    }
                }
                else if (c == '}') {
                    if (stack.isEmpty() || stack.peek() != '{'){
                        return false;
                    }
                }
                else {
                    if (stack.isEmpty() || stack.peek() != '['){
                        return false;
                    }
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }



    public static void main(String []args){
        String s = "){";
        ValidBrackets validBrackets = new ValidBrackets();
        System.out.println(validBrackets.isValid(s));
    }
}
