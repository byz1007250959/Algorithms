package com.algorithms.normal;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * @author DUAN
 * @date 2019/12/27
 */
public class MinStack {

    private List<Integer> list;
    private int min = Integer.MAX_VALUE;
    private int top;
    PriorityQueue<Integer> minHeap;
    /** initialize your data structure here. */
    public MinStack() {
        list = new LinkedList<>();
        minHeap =  new PriorityQueue<>();
    }

    public void push(int x) {
        if (x < min){
            this.min = x;
        }
        list.add(x);
        minHeap.offer(x);
        top++;
    }

    public void pop() {
        if (top != 0){
            int n = list.remove(--top);
            minHeap.remove(n);
            if (n == min){
                if (!minHeap.isEmpty()){
                    this.min = minHeap.peek();
                }
                else {
                    this.min = Integer.MAX_VALUE;
                }
            }
        }
    }

    public int top() {
        return list.get(top-1);
    }

    public int getMin() {
        return this.min;
    }

    public static void main(String []args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.top();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.getMin();
        minStack.push(-2147483648);
        minStack.top();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }
}
