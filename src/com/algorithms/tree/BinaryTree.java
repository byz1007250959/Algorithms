package com.algorithms.tree;

import java.util.LinkedList;
import java.util.Stack;

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
     * 递归求树的深度
     * @param root 树的根节点
     * @return 树的深度
     * @author DUAN
     * @date 2018/11/14 17:03
     */
    private int getDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        return Math.max(getDepth(root.left),getDepth(root.right))+1;
    }

    /**
     * 非递归方式求树的深度
     * @param root 根节点
     * @return 树的深度
     * @author DUAN
     * @date 2018/11/15 11:06
     */
    private int getDepth2(TreeNode root){
        if(root==null){
            return 0;
        }
        //不为空定义当前深度，当前结点个数，下层节点个数
        int depth=0;
        int currentLevelNodes=1;
        int nextLevelNodes=0;
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            //移除结点并将当前层结点减1
            TreeNode currentNode=queue.removeFirst();
            currentLevelNodes--;
            if(currentNode.left!=null){
                //左子节点不为空，下一层节点个数加1
                queue.addLast(currentNode.left);
                nextLevelNodes++;
            }
            if(currentNode.right!=null){
                //右子节点不为空，下一层节点个数加1
                queue.addLast(currentNode.right);
                nextLevelNodes++;
            }
            if(currentLevelNodes==0){
                /*
                如果当前层节点个数为0，表示已经全部访问过，深度+1，置当前层节点
                个数为下一层节点个数，下一层结点个数清零
                 */
                depth++;
                currentLevelNodes=nextLevelNodes;
                nextLevelNodes=0;
            }
        }
        return depth;
    }

    /**
     * 递归求二叉树的结点个数
     * 非递归求节点个数的思想和层次遍历的思想一样，只需用队列保存节点
     * 每次弹出节点进行左右子是否空判断计数即可
     * @param root 根节点
     * @return 结点个数
     * @author DUAN
     * @date 2018/11/15 10:48
     */
    private int count(TreeNode root){
        if(root==null){
            return 0;
        }
        return count(root.left)+count(root.right)+1;
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
        visit(root);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 前序遍历的非递归方式实现
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/14 19:10
     */
    private void preOrderTraversal2(TreeNode root){
        if(root==null){
            return;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode currentNode=root;
        while (currentNode!=null||!stack.isEmpty()){
            //先访问左子节点，并且将左节点入栈，一直走到左树最深处
            while (currentNode!=null){
                visit(currentNode);
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            if(!stack.isEmpty()){
                //弹出左子节点,令当前节点为该节点的右子节点
                currentNode=stack.pop();
                currentNode=currentNode.right;
            }
        }
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
        visit(root);
        inOrderTraversal(root.right);
    }

    /**
     * 非递归方式的二叉树中序遍历
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/15 10:36
     */
    private void inOrderTraversal2(TreeNode root){
        if(root==null){
            return;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode currentNode=root;
        while (currentNode!=null||!stack.isEmpty()){
            while (currentNode!=null){
                //左子不为空时，一直将左子压入栈中
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            if(!stack.empty()){
                //左子节点从栈中弹出时，对该节点访问，并将当前结点置为改节点的右子节点
                currentNode=stack.pop();
                visit(currentNode);
                currentNode=currentNode.right;
            }
        }
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
        visit(root);
    }

    /**
     * 非递归方式的后续遍历
     * @param root 根节点
     * @author DUAN
     * @date 2018/11/15 13:57
     */
    private void postOrderTraversal2(TreeNode root){
        if(root==null){
            return;
        }
        //保存树节点
        Stack<TreeNode> stack1=new Stack<>();
        //保存后序遍历结果
        Stack<TreeNode> stack2=new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()){
            TreeNode currentNode=stack1.pop();
            stack2.push(currentNode);
            /*
            左节点先入栈右节点后入栈，这样在弹出之后再
            压入栈2。这样右结点会在栈2的底部从而达到后访问的目的
             */
            if(currentNode.left!=null){
                stack1.push(currentNode.left);
            }
            if(currentNode.right!=null){
                stack1.push(currentNode.right);
            }
        }
        while (!stack2.isEmpty()){
            visit(stack2.pop());
        }
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
            visit(currentNode);
            if(currentNode.left!=null){
                queue.addLast(currentNode.left);
            }
            if(currentNode.right!=null){
                queue.addLast(currentNode.right);
            }
        }
    }

    /**
     * 访问一个结点
     * @param node 结点
     */
    private void visit(TreeNode node){
        if(node==null){
            return;
        }
        System.out.print(node.value+" ");
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
        binaryTree.preOrderTraversal(root);
        System.out.println("递归方式前序遍历");
        binaryTree.preOrderTraversal2(root);
        System.out.println("非递归方式前序遍历");
        binaryTree.inOrderTraversal(root);
        System.out.println("递归方式中序遍历");
        binaryTree.inOrderTraversal2(root);
        System.out.println("非递归方式中序遍历");
        binaryTree.postOrderTraversal(root);
        System.out.println("递归方式的后序遍历");
        binaryTree.postOrderTraversal2(root);
        System.out.println("非递归方式的后续遍历");
        binaryTree.levelOrderTraversal(root);
        System.out.println("二叉树的层次遍历(广度优先搜索)");
        System.out.println("递归方式求树的深度:"+binaryTree.getDepth(root));
        System.out.println("非递归方式求树的深度:"+binaryTree.getDepth2(root));
        System.out.println("递归方式求树的结点个数:"+binaryTree.count(root));
    }
}
