package com.algorithms.normal;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * @author DUAN
 * @date 2019/12/18
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3){
            return 0;
        }
        int length = nums.length;
        int sum = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < length - 2; i++) {
            int first = i + 1;
            int last = length - 1;
            while (first < last) {
                int curSum = nums[i] + nums[first] + nums[last];
                if (Math.abs(curSum - target) < Math.abs(sum - target)){
                    sum = curSum;
                }
                if (curSum > target){
                    last--;
                }
                else if (curSum < target){
                    first++;
                }
                else {
                    return sum;
                }
            }
        }
        return sum;
    }

    public static void main(String []args){
        ThreeSumClosest sumClosest = new ThreeSumClosest();
        int []array = {1,2,4,8,16,32,64,128};
        System.out.println(sumClosest.threeSumClosest(array,82));
    }

}
