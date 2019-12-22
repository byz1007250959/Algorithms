package com.algorithms.normal;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * @author DUAN
 * @date 2019/12/22
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++){
            sb.append(digits[i]);
        }
        int carry = 0;
        int length = sb.length();
        int last = sb.charAt(length - 1) - '0';
        if (last + 1 >= 10){
            carry = 1;
            last = last + 1 - 10;
            sb.setCharAt(length-1,(char) (last+'0'));
        }
        else {
            last++;
            sb.setCharAt(length-1,(char) (last+'0'));
            return toIntArray(sb);
        }
        int j = length - 2;
        while (carry != 0 && j >= 0){
            char c = sb.charAt(j);
            int n = c - '0';
            if (n + carry >= 10){
                carry = 1;
                n = n + carry - 10;
                sb.setCharAt(j,(char)(n+'0'));
                j--;
            }
            else {
                n = n + carry ;
                sb.setCharAt(j,(char)(n+'0'));
                carry = 0;
            }
        }
        if (carry == 1){
            sb.insert(0,1);
        }
        return toIntArray(sb);
    }

    private int[] toIntArray(StringBuilder sb){
        int[] array = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++){
            array[i] = sb.charAt(i) - '0';
        }
        return array;
    }

    public static void main(String []args){
        PlusOne plusOne = new PlusOne();
        int []a = {9,8,9,9};
        for (int i:plusOne.plusOne(a)){
            System.out.print(i+" ");
        }
    }
}
