package com.algorithms.normal;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * @author DUAN
 * @date 2020/1/8
 */
public class MaxArea {

    /**
     * 双指针法O(n)
     * @author DUAN
     * @date 2020/1/8 11:06
     */
    public int maxArea(int[] height) {
        int curMax = 0;
        for (int left = 0,right = height.length - 1; left < right;) {
            int area;
            if (height[left] < height[right]) {
                area = (right - left) * height[left];
                left++;
            }
            else {
                area = (right - left) * height[right];
                right--;
            }
            if (area > curMax) {
                curMax = area;
            }
        }
        return curMax;
    }

    /**
     * 暴力穷举O(n^2)
     * @author DUAN
     * @date 2020/1/8 11:07
     */
    public int maxArea2(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length -1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int mul = Math.min(height[i],height[j]) * (j - i);
                if (mul > max) {
                    max = mul;
                }
            }
        }
        return max;
    }

    public static void main (String []args) {
        int a[] = {1,2,3,4,5,6,7,8,8};
        MaxArea maxArea = new MaxArea();
        maxArea.maxArea(a);
    }
}
