package com.algorithms.tree;

/**
 * 最小堆解决topK问题(前k大的数字)
 * @author DUAN
 * @date 2019/10/11
 */
public class MinHeap {
    /**
     * 存储元素
     */
    private int[] heap;
    /**
     * 堆最大大小
     */
    private int maxSize;
    /**
     * 当前大小
     */
    private int size;

    public MinHeap(int maxSize){
        /*
        堆容量初始化为最大大小+1，元素下标从1开始放置
         */
        this.maxSize = maxSize;
        heap = new int[maxSize + 1];
    }

    /**
     * 堆中插入一个元素
     * @param value 新增值
     * @return 如果新增返回null，如果替换了旧值返回旧值
     * @author DUAN
     * @date 2019/10/11 10:31
     */
    public Integer add(int value){
        if (size == maxSize){
            //堆已经满,与堆顶比较抛弃值或者替换
            return replace(value);
        }
        else {
            heap[++size] = value;
            int i = size;
            while (i != 1 && value < heap[i / 2]){
                heap[i] = heap[i /2];
                i = i / 2;
            }
            heap[i] = value;
            return null;
        }
    }

    /**
     * @param value 新值
     * @return 替换后的旧值或者原值
     * @author DUAN
     * @date 2019/10/11 11:23
     */
    private Integer replace(int value){
        int topValue = heap[1];
        if (value < topValue){
            //value比堆顶元素还小，抛弃value
            return value;
        }
        else {
            /*
            新值比堆顶大，堆顶元素被抛弃，新值下沉到堆中合适位置
             */
            int i = 1;
            while (i < maxSize){
                if (i * 2 > maxSize){
                    //结点没有左子节点了
                    break;
                }
                else if (i * 2 == maxSize){
                    //节点只有一个左子节点
                    int leftSonVal = heap[i * 2];
                    if (value > leftSonVal){
                        heap[i] = heap[i * 2];
                        i = i *2;
                    }
                }
                else {
                    //具备两个节点
                    int leftSonVal = heap[i * 2];
                    int rightSonVal = heap[i * 2 + 1];
                    if (value < leftSonVal && value < rightSonVal){
                        //堆顶值仍然最小
                        break;
                    }
                    else{
                        //堆顶值需要下沉
                        if (leftSonVal < rightSonVal){
                            //左孩子值小，向左下沉
                            heap[i] = heap[i * 2];
                            i = i * 2;
                        }
                        else {
                            //右孩子值小，向右下沉
                            heap[i] = heap[i * 2 + 1];
                            i = i * 2 + 1;
                        }
                    }
                }
            }
            heap[i] = value;
        }
        return topValue;
    }

    public void printHeap(){
        for (int i = 1; i <= size; i++){
            System.out.print(heap[i] + " ");
        }
    }

    public static void main (String[] args){
        MinHeap minHeap = new MinHeap(6);
        int []array = {58,32,73,20,31,95,46,77,22,67,70,36,99,100,105};
        for (int i = 0; i < array.length; i++){
            minHeap.add(array[i]);
        }
        minHeap.printHeap();
    }
}
