package com.algorithms.normal;

/**
 * 输入非空字符串质保函数字1和0，返回他们的和(二进制表示)
 * leetCode 67题
 * @author DUAN
 * @date 2019/12/16
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int curSum;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >=0){
            curSum = carry;
            if (i >= 0){
                curSum += a.charAt(i) - '0';
                --i;
            }
            if (j >= 0){
                curSum += b.charAt(j) - '0';
                --j;
            }
            if (curSum >= 2){
                carry = 1;
                curSum -= 2;
                sb.insert(0,curSum);
            }
            else {
                carry = 0;
                sb.insert(0,curSum);
            }
        }
        if (carry == 1){
            sb.insert(0,1);
        }
        return sb.toString();
    }

    public static void main(String []args){
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101","110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));
    }
}
