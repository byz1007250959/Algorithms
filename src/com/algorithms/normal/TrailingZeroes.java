package com.algorithms.normal;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * @author DUAN
 * @date 2019/12/26
 */
public class TrailingZeroes {

    public int trailingZeroes(int n) {
        int num = 0;
        for (int i = 5; i <= n; i += 5){
            int j = i;
            while (j % 5 == 0) {
                num++;
                j /= 5;
            }
        }
        return num;
    }

    public static void main(String []args){
        TrailingZeroes trailingZeroes = new TrailingZeroes();
        System.out.println(trailingZeroes.trailingZeroes(1808548329));
    }
}
