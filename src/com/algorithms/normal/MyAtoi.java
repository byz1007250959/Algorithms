package com.algorithms.normal;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 * @author DUAN
 * @date 2020/1/7
 */
public class MyAtoi {

    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;
        int i = 0;
        while (i < str.length() && str.charAt((i)) == ' ') {
            i++;
        }
        if (i == str.length()) {
            return 0;
        }
        char first = str.charAt(i);
        if (first != '-' && first != '+' &&!(first >= '0' && first <= '9')) {
            return 0;
        }
        if (first == '-') {
            isNegative = true;
            i++;
        }
        if (first == '+') {
            i++;
        }
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            }
            else {
                break;
            }
        }
        i = 0;
        //去除前导0
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        if (i == sb.length()){
            return 0;
        }
        String numStr = sb.substring(i);
        if (numStr.length() == 0) {
            return 0;
        }
        else if (numStr.length() > 10 && isNegative) {
            return Integer.MIN_VALUE;
        }
        else if (numStr.length() > 10 && !isNegative) {
            return Integer.MAX_VALUE;
        }
        else {
            return transform(numStr,isNegative);
        }
    }

    private int transform(String numStr, boolean isNegative) {
        long ret = 0;
        for (int j = 0; j < numStr.length(); j++) {
            int c = numStr.charAt(j) - '0';
            ret = ret * 10 + c;
        }
        if (ret > Integer.MAX_VALUE && isNegative) {
            return Integer.MIN_VALUE;
        } else if (ret > Integer.MAX_VALUE && !isNegative) {
            return Integer.MAX_VALUE;
        } else {
            if (isNegative) {
                return 0 - (int) ret;
            } else {
                return (int) ret;
            }
        }
    }

    public static void main(String []args) {
        MyAtoi myAtoi = new MyAtoi();
        System.out.println(myAtoi.myAtoi(" "));
    }
}
