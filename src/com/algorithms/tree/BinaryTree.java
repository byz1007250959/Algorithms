package com.algorithms.tree;

import java.util.LinkedList;

/**
 * 二叉树常用的算法总结
 * @author DUAN
 * @date 2018/11/13
 */
public class BinaryTree {


    /**
     * 根据层次遍历的顺序来创建一颗二叉树
     * 值为null表示结点为空
     * @param valueQueue 结点值的队列
     * @author DUAN
     * @date 2018/11/13 17:40
     */
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
            int value1;
            int value2;
            //取值队列两个值赋给当前结点的左子和右子
            if(!valueQueue.isEmpty()){
                value1=valueQueue.removeFirst();
                currentNode.left=new TreeNode(value1);
                treeQueue.addLast(currentNode.left);
            }
            if(!valueQueue.isEmpty()){
                value2=valueQueue.removeFirst();
                currentNode.right=new TreeNode(value2);
                treeQueue.addLast(currentNode.right);
            }
            //值队列空则构建结束
            if(valueQueue.isEmpty()){
                break;
            }
        }
        return root;
    }

    /**
     * 求树的深度
     * @param root 树的根节点
     * @return 树的深度
     * @author DUAN
     * @date 2018/11/14 17:03
     */
    private int getDeep(TreeNode root){
        if(root==null){
            return 0;
        }
        return Math.max(getDeep(root.left),getDeep(root.right))+1;
    }

    /**
     * 递归方式的前序遍历
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/13 18:18
     */
    private void preOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        System.out.print(root.value+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 递归方式的中序遍历
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/14 17:10
     */
    private void inOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value+" ");
        inOrderTraversal(root.right);
    }

    /**
     * 递归方式的后序遍历
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/14 17:13
     */
    private void postOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value+" ");
    }

    /**二叉树的层次遍历
     * 层次遍历相当于广度优先搜索，具体算法思想是：首先根节点如队列，当队列不为空时候
     * 每次弹出第一个结点进行访问，如果左子或者右子节点不为空则加入到队列中
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/14 17:18
     */
    private void levelOrderTraversal(TreeNode root){
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()){
            TreeNode currentNode=queue.removeFirst();
            System.out.print(currentNode.value+" ");
            if(currentNode.left!=null){
                queue.addLast(currentNode.left);
            }
            if(currentNode.right!=null){
                queue.addLast(currentNode.right);
            }
        }
    }


    /**
     * 树节点定义
     */
    private static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value){
            this.value=value;
        }
    }

    public static void main(String[] args){
        LinkedList<Integer> values=new LinkedList<>();
        for(int i=0;i<10;i++){
            values.add(i);
        }
        BinaryTree binaryTree=new BinaryTree();
        TreeNode root=binaryTree.createTree(values);
        long a=System.currentTimeMillis();
        binaryTree.preOrderTraversal(root);
        System.out.println();
        binaryTree.inOrderTraversal(root);
        System.out.println();
        binaryTree.postOrderTraversal(root);
        System.out.println();
        binaryTree.levelOrderTraversal(root);
    }
}
