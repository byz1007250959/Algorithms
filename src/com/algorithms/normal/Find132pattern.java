package com.algorithms.normal;

import java.util.Stack;

/**
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
 * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * @author DUAN
 * @date 2019/12/19
 */
public class Find132pattern {

    /**
     * 超时解法
     * @param  nums 输入数组
     * @return 是否存在
     * @author DUAN
     * @date 2019/12/19 16:12
     */
    public boolean find132pattern(int[] nums) {
        boolean ret = false;
        if (nums == null || nums.length < 3){
            return ret;
        }
        int length = nums.length;
        for (int i = 0; i < length - 2; i++){
            int left = i + 1;
            while (left != length - 1){
                if (nums[left] <= nums[i]){
                    //aj小于等于ai left右移
                    left++;
                    continue;
                }
                int right = length - 1;
                while (right > left){
                    if (nums[i] < nums[right] && nums[right] < nums[left]){
                        ret = true;
                        return ret;
                    }
                    right--;
                }
                left++;
            }
        }
        return ret;
    }

    /**
     * 官方题解：构造j的前缀最小值数组(aj位置之前的最小值，作为ai的候选值)
     * 使用栈来保存ak的候选值，从后向前遍历。
     * @author DUAN
     * @date 2019/12/24 10:39
     */
    public boolean find132pattern2(int[] nums) {
        if (nums == null || nums.length < 3){
            return false;
        }
        int length = nums.length;
        int []minArray = new int[length];
        //构造一个aj的前缀最小值数组
        minArray[0] = nums[0];
        for (int i = 1; i < length; i++){
            minArray[i] = Math.min(nums[i],minArray[i-1]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int j = length - 1; j >= 0; j--){
            if (nums[j] > minArray[j]){
                while (!stack.isEmpty() && stack.peek() <= minArray[j]){
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]){
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }

    public static void main(String []args){
        int []input = {-2,1,-1};
        Find132pattern find132pattern = new Find132pattern();
        System.out.println(find132pattern.find132pattern2(input));
    }
}
