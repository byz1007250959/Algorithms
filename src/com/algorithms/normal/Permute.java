package com.algorithms.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * https://leetcode-cn.com/problems/permutations/
 * @author DUAN
 * @date 2020/1/3
 */
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        search(nums,0,nums.length - 1,retList);
        return retList;
    }

    private void search(int[] nums, int start, int end,List<List<Integer>> retList) {
        if (start == end) {
            List<Integer> oneResult = new ArrayList<>();
            for (int i : nums) {
                oneResult.add(i);
            }
            retList.add(oneResult);
        }
        for (int i = start, j = start; j <= end; j++){
            swap(nums,i,j);
            search(nums,start+1,end,retList);
            swap(nums,i,j);
        }
    }

    private void swap(int[] nums,int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
