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

    /**求第k层节点的个数
     * @param root 根节点
     * @param  k 第k层
     * @return 第k层节点的数目
     * @author DUAN
     * @date 2018/12/5 14:17
     */
    private int getKLevelNodeNumber(TreeNode root, int k){
        if(root==null||k<1){
            return 0;
        }
        if(k==1){
            return 1;
        }
        return getKLevelNodeNumber(root.left,k-1)+getKLevelNodeNumber(root.right,k-1);
    }

    /**
     * 递归求二叉树中叶子结点的个数
     * @param root 根节点
     * @return 这棵树叶子结点个数
     * @author DUAN
     * @date 2018/12/5 14:28
     */
    private int getLeafNodeNumber(TreeNode root){
        if(root==null){
            return 0;
        }
        if(root.left == null && root.right==null){
            return 1;
        }
        return getLeafNodeNumber(root.left)+getLeafNodeNumber(root.right);
    }

    /**
     * 非递归求解二叉树中叶子结点个数
     * @param root 根节点
     * @return 叶子结点个数
     * @author DUAN
     * @date 2018/12/5 14:34
     */
    private int getLeafNodeNumber2(TreeNode root){
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        TreeNode currentNode;
        int leafCount=0;
        while (!queue.isEmpty()){
            currentNode=queue.removeFirst();
            if(currentNode.left == null && currentNode.right==null){
                leafCount++;
            }
            if(currentNode.left != null){
                queue.addLast(currentNode.left);
            }
            if(currentNode.right !=null){
                queue.addLast(currentNode.right);
            }
        }
        return leafCount;
    }

    /**
     * 判断两棵二叉树是否相同
     * 思路：如果根节点都是null则返回true
     * 如果其中一颗树为null另一个非null返回false
     * 如果以上满足并且节点的值相同则返回true否则返回false
     * @param root1 树1的根节点
     * @param root2 树2的根节点
     * @return 布尔值
     * @author DUAN
     * @date 2019/3/18 10:46
     */
    private boolean isSameTree(TreeNode root1,TreeNode root2){
        if (root1 == null && root2 == null){
            return true;
        }
        else if (root1 == null || root2 == null){
            return false;
        }
        if (root1.value != root2.value){
            return false;
        }
        return isSameTree(root1.left,root2.left) && isSameTree(root1.right,root2.right);
    }

    /**
     * 非递归方式判断两棵树是否相同
     * 思路：首先判断两个根节点是否为空，都为空返回true
     * 如果一颗为空一颗不为空返回false
     * 经过以上判断将两个根节点分别加入两个栈中，
     * 对栈中节点弹出进行后序判断，见代码
     * @param root1 第一棵树根节点
     * @param root2 第二颗树根节点
     * @return 布尔值
     * @author DUAN
     * @date 2019/3/18 11:11
     */
    private boolean isSameTree2(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 ==null){
            return true;
        }
        else if (root1 == null || root2 == null){
            return false;
        }
        //运行到此说明两个根节点都不为空，分别入栈
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root1);
        stack2.push(root2);
        while (!stack1.empty() && !stack2.empty()){
            TreeNode node1 = stack1.pop();
            TreeNode node2 = stack2.pop();
            if (node1 == null && node2 == null){
                continue;
            }
            else if (node1 != null && node2 != null && node1.value == node2.value){
                stack1.push(node1.left);
                stack1.push(node1.right);
                stack2.push(node2.left);
                stack2.push(node2.right);
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是AVL树
     * 平衡二叉树要保证树的左树最深和右树最深相差不超过1
     * 利用前面的算二叉树深度函数递归判断二叉树是否是avl树
     * @param root 树根节点
     * @return 布尔值
     * @author DUAN
     * @date 2019/3/18 14:37
     */
    private boolean isAVLTree(TreeNode root){
        if (root==null){
            return true;
        }
        if (Math.abs(getDepth(root.left)-getDepth(root.right))>1){
            return false;
        }
        return isAVLTree(root.left) && isAVLTree(root.right);
    }

    /**
     * 寻找两个树节点的最近公共祖先节点,不存在则返回null
     * 思路：如果一个结点在根节点左树另一个结点在根节点右树
     * 则最近公共祖先为根节点。如果两个节点都在根节点的左树或者右树
     * 则以左节点或右节点为根递归进行查找。
     * @param root 根节点
     * @param node1 第一个树节点
     * @param node2 第二个树节点
     * @return 最近公共祖先节点，如果没有则返回null
     * @author DUAN
     * @date 2019/3/19 10:58
     */
    private TreeNode findLastCommonParentNode(TreeNode root, TreeNode node1, TreeNode node2){
        if (root == null){
            return null;
        }
        //判断两个节点是否与根节点相同
        if (root.value == node1.value){
            /*
            如果根节点等于节点1，查找节点2是否存在
            如果存在返回根节点，不存在返回null
             */
            if (findNodeExist(root,node2)){
                return root;
            }
            else {
                return null;
            }
        }
        else if (root.value == node2.value){
            /*
            如果根节点等于节点2，查找节点1是否存在
            如果存在返回根节点，不存在返回null
             */
            if (findNodeExist(root,node1)){
                return root;
            }
            else {
                return null;
            }
        }
        //两个节点不是根节点，进一步在左树和右树查找
        if (findNodeExist(root.left,node1)){
            //节点1存在于根节点左树
            if (findNodeExist(root.right,node2)){
                //节点2存在于根节点右树，返回根节点
                return root;
            }
            else if (findNodeExist(root.left,node2)){
                //如果两个节点都在左树，以左孩子为根递归寻找
                return findLastCommonParentNode(root.left,node1,node2);
            }
            else {
                //node2不存在,返回null
                return null;
            }
        }
        else if (findNodeExist(root.right,node1)){
            //节点1存在于根节点右树
            if (findNodeExist(root.left,node2)){
                //节点2存在于根节点左树
                return root;
            }
            else if (findNodeExist(root.right,node2)){
                return findLastCommonParentNode(root.right,node1,node2);
            }
            else {
                return null;
            }
        }
        else {
            //node1不存在，没有公共节点返回null
            return null;
        }
    }

    /**
     * 判断当前结点是否在当前根节点下
     * @param root 当前根节点
     * @param node 待查找的结点
     * @return 布尔值
     * @author DUAN
     * @date 2019/3/19 11:01
     */
    private boolean findNodeExist(TreeNode root, TreeNode node){
        if (root == null){
            return false;
        }
        if (root.value == node.value){
            return true;
        }
        return findNodeExist(root.left,node) || findNodeExist(root.right,node);
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
//        binaryTree.preOrderTraversal(root);
//        System.out.println("递归方式前序遍历");
//        binaryTree.preOrderTraversal2(root);
//        System.out.println("非递归方式前序遍历");
//        binaryTree.inOrderTraversal(root);
//        System.out.println("递归方式中序遍历");
//        binaryTree.inOrderTraversal2(root);
//        System.out.println("非递归方式中序遍历");
//        binaryTree.postOrderTraversal(root);
//        System.out.println("递归方式的后序遍历");
//        binaryTree.postOrderTraversal2(root);
//        System.out.println("非递归方式的后续遍历");
//        binaryTree.levelOrderTraversal(root);
//        System.out.println("二叉树的层次遍历(广度优先搜索)");
//        System.out.println("递归方式求树的深度:"+binaryTree.getDepth(root));
//        System.out.println("非递归方式求树的深度:"+binaryTree.getDepth2(root));
//        System.out.println("递归方式求树的结点个数:"+binaryTree.count(root));
//        System.out.println("求第三层节点个数:"+binaryTree.getKLevelNodeNumber(root,3));
//        System.out.println("递归求叶子结点个数:"+binaryTree.getLeafNodeNumber(root));
//        System.out.println("非递归求叶子结点个数:"+binaryTree.getLeafNodeNumber2(root));
//        LinkedList<Integer> values2=new LinkedList<>();
//        for(int i=0;i<10;i++){
//            values2.add(i);
//        }
//        TreeNode root2 = binaryTree.createTree(values2);
//        System.out.println(binaryTree.isSameTree(root,root2));
//        System.out.println(binaryTree.isSameTree2(root,root2));
//        System.out.println(binaryTree.isAVLTree(root));
        TreeNode node1 =new TreeNode(7);
        TreeNode node2 =new TreeNode(9);
        TreeNode commonRoot=binaryTree.findLastCommonParentNode(root,node1,node2);
        if(commonRoot!=null){
            System.out.println(commonRoot.value);
        }

    }
}
