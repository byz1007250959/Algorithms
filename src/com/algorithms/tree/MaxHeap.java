package com.algorithms.tree;

/**
 * 最大堆(通过传入数组构造最大堆)
 * @author DUAN
 * @date 2019/10/10
 */
public class MaxHeap {
    /**
     * 存储数据元素
     */
    private int []heap;
    /**
     * 堆大小
     */
    private int size;

    /**
     * 初始化最大堆
     */
    public MaxHeap(int []array){
        this.size = array.length;
        //堆大小初始化为size+1，从下标1开始放置元素
        heap = new int[size+1];
        for (int i = 1; i <= size; i++){
            heap[i] = array[i-1];
        }
        /*
        从最后一个父节点开始构造最大堆
        由完全二叉树的性质可以知道(size/2)的下标就是最后一个父节点
         */
        for (int j = size / 2; j > 0;j--){
            int curParentVal = heap[j];
            //起初指向父节点的左孩子
            int sonIndex = j * 2;
            /*
            循环一直到叶子结点，将parent的值合适的调整
             */
            while (sonIndex <= size){
                if (sonIndex < size && heap[sonIndex] < heap[sonIndex + 1]){
                    //父节点同时具有左右孩子并且右孩子更大,指向右孩子
                    sonIndex++;
                }
                if (heap[sonIndex] < curParentVal){
                    //子节点没有父节点大，不需要操作
                    break;
                }
                else {
                    //子节点比父节点大，将父节点赋值为子节点
                    heap[sonIndex / 2] = heap[sonIndex];
                    sonIndex *= 2;
                }
            }
            heap[sonIndex / 2] = curParentVal;
        }
    }

    public void printHeap(){
        for (int i = 1; i <= size; i++){
            System.out.print(heap[i] + " ");
        }
    }

    public static void main (String []args){
        int []array = {4,1,3,2,16,9,10,14,8,7,15};
        MaxHeap heap = new MaxHeap(array);
        heap.printHeap();
    }
}
