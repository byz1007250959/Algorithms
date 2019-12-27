package com.algorithms.normal;

/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 * https://leetcode-cn.com/problems/reverse-bits/
 * @author DUAN
 * @date 2019/12/26
 */
public class ReverseBits {

    public int reverseBits(int n) {
        int mask = 1;
        int i = 0;
        int ret = 0;
        while(i < 31){
            i++;
            ret += (n & mask) >> (i-1);
            ret <<= 1;
            mask <<= 1;
        }
        return n >= 0 ? ret : ret + 1;
    }
    public static void main(String []args) {
        ReverseBits reverseBits = new ReverseBits();
        System.out.println(reverseBits.reverseBits(43261596));
        System.out.println(Integer.toUnsignedString(reverseBits.reverseBits((int)4294967293L)));
    }
}
