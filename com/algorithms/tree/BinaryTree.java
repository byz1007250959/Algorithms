package algorithms.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树常用的算法总结
 * @author DUAN
 * @date 2018/11/13
 */
public class BinaryTree {


    /**
     * 根据层次遍历的顺序来创建一颗二叉树
     * 值为null表示结点为空
     * @param values 结点值的列表
     * @author DUAN
     * @date 2018/11/13 17:40
     */
    public TreeNode createTree(List<Integer> values){
        //初始化根节点
        Integer rootValue=values.get(0);
        TreeNode root=new TreeNode(rootValue);
        //值入队列，根节点入队列
        LinkedList<Integer> valueQueue=new LinkedList<>();
        LinkedList<TreeNode> treeQueue=new LinkedList<>();
        treeQueue.add(root);
        for(int i=1;i<values.size();i++){
            valueQueue.add(values.get(i));
        }
        while (!treeQueue.isEmpty()){
            //弹出队列第一个结点
            TreeNode currentNode=treeQueue.removeFirst();
            int value1;
            int value2;
            //取值队列两个值赋给当前结点的左子和右子
            if(!valueQueue.isEmpty()){
                value1=valueQueue.removeFirst();
                currentNode.left=new TreeNode(value1);
            }
            if(!valueQueue.isEmpty()){
                value2=valueQueue.removeFirst();
                currentNode.right=new TreeNode(value2);
            }
            //值队列空则构建结束
            if(valueQueue.isEmpty()){
                break;
            }
        }
        return root;
    }

    /**
     * 递归方式的前序遍历
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/13 18:18
     */
    private void preorderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        System.out.print(root.value+" ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
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
        List<Integer> values=new ArrayList<>();
        for(int i=0;i<10;i++){
            values.add(i);
        }
        BinaryTree binaryTree=new BinaryTree();
        TreeNode root=binaryTree.createTree(values);
        binaryTree.preorderTraversal(root);
    }
}
