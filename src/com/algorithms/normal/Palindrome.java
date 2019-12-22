package com.algorithms.normal;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * https://leetcode-cn.com/problems/valid-palindrome/
 * @author DUAN
 * @date 2019/12/22
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()){
            return true;
        }
        for (int i = 0,j = s.length() - 1; i <= j; i++, j--) {
            while (i <= j && !isLegalChar(s.charAt(i)))
                i++;
            while (i <= j && !isLegalChar(s.charAt(j)))
                j--;
            if (i >= j){
                return true;
            }
            char left = s.charAt(i);
            char right = s.charAt(j);
            if ((left >= 'A' && left <= 'Z') && (right >= 'a' && right <= 'z')){
                left += 32;
            }
            else if ((left >= 'a' && left <= 'z') && (right >= 'A' && right <= 'Z')){
                right += 32;
            }
            if (left != right)
                return false;
        }
        return true;
    }

    private boolean isLegalChar(char c){
        if (c >= 'A' && c <= 'Z'){
            return true;
        }
        if (c >= 'a' && c <= 'z'){
            return true;
        }
        if (c >= '0' && c <= '9'){
            return true;
        }
        return false;
    }

    public static void main(String []args){
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome("0P"));
    }

}
