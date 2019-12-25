package com.algorithms.normal;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * @author DUAN
 * @date 2019/12/25
 */
public class Rob {

    /**
     * 动态规划解法
     * 在i位置上可以获得的最大钱满足
     * dp[i] = MAX( dp[i-1], dp[i-2] + num )
     * @author DUAN
     * @date 2019/12/25 17:31
     */
    public int rob(int[] nums) {
        if (nums == null){
            return 0;
        }
        if (nums.length == 1 || nums.length == 0){
            return nums[0];
        }
        int []dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length -1];
    }

    public static void main(String []args) {
        Rob rob = new Rob();
        int num[] = {2,7,9,3,1};
        System.out.println(rob.rob(num));
    }
}
