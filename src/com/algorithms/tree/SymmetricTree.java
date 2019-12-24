package com.algorithms.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * leetcode 101题
 * @author DUAN
 * @date 2019/12/23
 */
public class SymmetricTree {

    /**
     * 判断二叉树是否是镜像二叉树(超时)
     * @param root 根节点
     * @return 布尔值
     * @author DUAN
     * @date 2019/12/23 15:34
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        int depth = getDepth(root);
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.addFirst(root);
        /*
        层次遍历所有结点，空节点的值标记为0，存入数字队列
        此处标记0的作用是将null结点也进行填充，
         */
        int level = 1;
        int leafCount = 1;
        int count = 0;
        LinkedList<Integer> numQueue = new LinkedList<>();
        while (!queue.isEmpty() && level <= depth){
            TreeNode currentNode=queue.removeFirst();
            numQueue.add(currentNode.val);
            count++;
            if(currentNode.left!=null){
                queue.addLast(currentNode.left);
            }
            else {
                queue.addLast(new TreeNode(Integer.MAX_VALUE));
            }
            if(currentNode.right!=null){
                queue.addLast(currentNode.right);
            }
            else {
                queue.addLast(new TreeNode(Integer.MAX_VALUE));
            }
            if (count == leafCount){
                level++;
                leafCount *= 2;
                count = 0;
                if (!judge(numQueue)){
                    return false;
                }
                numQueue.clear();
            }
        }
        return true;
    }

    /**
     * 官方题解(该问题转化成两个数什么情况下互为镜像)
     * 1:根节点相同
     * 2:每个树的右子树都与另一个树的左子树对称
     * @author DUAN
     * @date 2019/12/23 17:03
     */
    public boolean isSymmetric2(TreeNode root) {
        return isMirror(root,root);
    }

    /**
     * 非递归官方题解
     * @author DUAN
     * @date 2019/12/23 17:08
     */
    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    private boolean judge(LinkedList<Integer> nums){
        if (nums.size() == 1){
            return true;
        }
        int length = nums.size();
        for (int i = 0,j = length - 1; i <= j; i++, j--) {
            int left = nums.get(i);
            int right = nums.get(j);
            if (left != right){
                return false;
            }
        }
        return true;
    }

    private int getDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        return Math.max(getDepth(root.left),getDepth(root.right))+1;
    }

     private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
     }

    public TreeNode createTree(LinkedList<Integer> valueQueue){
        //初始化根节点
        Integer rootValue=valueQueue.removeFirst();
        TreeNode root=new TreeNode(rootValue);
        //根节点入队列
        LinkedList<TreeNode> treeQueue=new LinkedList<>();
        treeQueue.add(root);
        while (!treeQueue.isEmpty()){
            //弹出队列第一个结点
            TreeNode currentNode=treeQueue.removeFirst();
            Integer value1;
            Integer value2;
            //取值队列两个值赋给当前结点的左子和右子
            if(!valueQueue.isEmpty()){
                value1=valueQueue.removeFirst();
                if (value1 != null){
                    currentNode.left=new TreeNode(value1);
                    treeQueue.addLast(currentNode.left);
                }
            }
            if(!valueQueue.isEmpty()){
                value2=valueQueue.removeFirst();
                if (value2 != null){
                    currentNode.right=new TreeNode(value2);
                    treeQueue.addLast(currentNode.right);
                }
            }
            //值队列空则构建结束
            if(valueQueue.isEmpty()){
                break;
            }
        }
        return root;
    }

    public static void main(String []args) {
        Map<Integer,Integer> map = new HashMap<>();
        LinkedList<Integer> values = new LinkedList<>();
        values.add(1);
        values.add(2);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(4);
        values.add(3);
        LinkedList<Integer> values2 = new LinkedList<>();
        values2.add(1);
        values2.add(0);
//        values2.add(2);
//        values2.add(null);
//        values2.add(3);
//        values2.add(null);
//        values2.add(3);
        SymmetricTree symmetricTree = new SymmetricTree();
//        TreeNode tree1 = symmetricTree.createTree(values);
        TreeNode tree2 = symmetricTree.createTree(values2);
//        System.out.println(symmetricTree.isSymmetric(tree1));
        System.out.println(symmetricTree.isSymmetric(tree2));
    }
}
