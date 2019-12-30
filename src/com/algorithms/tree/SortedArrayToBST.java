package com.algorithms.tree;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * @author DUAN
 * @date 2019/12/30
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode head = chooseHead(nums,0,nums.length);
        return head;
    }

    private TreeNode chooseHead(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int half;
        if (start == 0){
            half = (end - start) / 2;
        }
        else {
            half = (end + start) / 2;
        }
        TreeNode head = new TreeNode(nums[half]);
        head.left = chooseHead(nums,start,half);
        head.right = chooseHead(nums,half+1,end);
        return head;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }
}
