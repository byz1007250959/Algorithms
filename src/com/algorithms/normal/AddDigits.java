package com.algorithms.normal;

/**
 * 各位相加(leetcode 258题)
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * @author DUAN
 * @date 2019/12/16
 */
public class AddDigits {

    public int addDigits(int num) {
        while(num >= 10){
            int sum = 0;
            while(num >= 10){
                sum += num % 10;
                num = num / 10;
            }
            sum += num;
            num = sum;
        }
        return num;
    }

    public int addDigits2(int num){
        if(num > 9){
            num = num % 9;
            if (num == 0){
                return 9;
            }
        }
        return num;
    }

    public static void main(String []args){
        AddDigits addDigits = new AddDigits();
        System.out.println(addDigits.addDigits(38));
    }
}
