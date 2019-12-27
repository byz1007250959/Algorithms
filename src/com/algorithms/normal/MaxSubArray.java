package com.algorithms.normal;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * https://leetcode-cn.com/problems/maximum-subarray/
 * @author DUAN
 * @date 2019/12/27
 */
public class MaxSubArray {

    /**
     * 贪心算法
     * 循环一遍，计算当前最大值，迄今为止所获得的的最大值
     * @author DUAN
     * @date 2019/12/27 16:06
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}
