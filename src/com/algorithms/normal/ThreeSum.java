package com.algorithms.normal;

import java.util.*;

/**
 * 三数之和，leetcode 15题
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * @author DUAN
 * @date 2019/12/17
 */
public class ThreeSum {

    /**
     * @param nums 输入数组
     * @return 返回的三元组集合
     * 暴力枚举解法(超时)
     * @author DUAN
     * @date 2019/12/17 14:57
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < length - 2; i++){
            for (int j = i + 1; j < length - 1; j++){
                for (int k = j + 1; k < length; k++){
                    if (nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.sort(null);
                        set.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 不超时解法
     * @param nums 输入数组
     * @return 三元组集合
     * @author DUAN
     * @date 2019/12/18 10:14
     */
    public List<List<Integer>> threeSun2(int[] nums){
        List<List<Integer>> retList = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return retList;
        }
        int length = nums.length;
        Arrays.sort(nums);
        if (nums[0] <= 0 && nums[length - 1] >= 0){
            //符号不相同才可能有结果
            for (int i = 0; i < length - 2; i++){
                if (nums[i] > 0){
                    //当前i大于0结果一定大于0
                    break;
                }
                if (i > 0 && nums[i] == nums[i-1]){
                    //前一个数字和当前数字相同会造成结果重复
                    continue;
                }
                int first = i + 1;
                int last = length - 1;
                while (first < last){
                    int threeSum = nums[i] + nums[first] + nums[last];
                    if (threeSum == 0){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[first]);
                        list.add(nums[last]);
                        retList.add(list);
                        //等于0时first的值连续相同会造成结果重复
                        while (nums[first] == nums[++first] && first < last) {}
                        //等于0时last的值连续相同会造成结果重复
                        while (nums[last] == nums[--last] && first < last) {}
                    }
                    else if (threeSum < 0){
                        //小于0说明first偏小，需要向右找一个大一些的值
                        while (nums[first] == nums[++first] && first < last) {}
                    }
                    else {
                        //小于0说明last偏大，需要向左找一个小一些的值
                        while (nums[last] == nums[--last] && first < last) {}
                    }
                }
            }
        }
        return retList;
    }

    public static void main(String []args){
        int []array = {-1,0,1,2,-1,-4};
        ThreeSum sum = new ThreeSum();
        List<List<Integer>> ret = sum.threeSun2(array);
        System.out.println(ret);
    }
}
