package com.algorithms.normal;

/**
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * @author DUAN
 * @date 2019/12/24
 */
public class Mysqrt {

    /**
     * 二分法，注意点是求取mid的值时候取右边的中位数
     * 否则可能会导致陷入死循环
     * long mid = (left + right + 1) / 2;
     * @author DUAN
     * @date 2019/12/25 17:34
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long left = 1;
        long right = x / 2;
        while (left < right) {
            long mid = (left + right + 1) / 2;
            long pow = mid * mid;
            if (pow > x) {
                right = mid -1;
            }
            else if (pow < x) {
                left = mid;
            }
            else {
                return (int)mid;
            }
        }
        return (int)left;
    }

    public static void main(String []args) {
        Mysqrt mysqrt = new Mysqrt();
        mysqrt.mySqrt(9);
    }

}
