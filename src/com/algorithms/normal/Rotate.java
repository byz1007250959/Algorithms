package com.algorithms.normal;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * https://leetcode-cn.com/problems/rotate-array/
 * @author DUAN
 * @date 2019/12/24
 */
public class Rotate {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        if (length < k)
            k = k % length;
        int []ret = new int[length];
        System.arraycopy(nums,length-k,ret,0,k);
        System.arraycopy(nums,0,ret,k,length-k);
        System.arraycopy(ret,0,nums,0,length);
    }

    public static void main(String []args) {

    }
}
