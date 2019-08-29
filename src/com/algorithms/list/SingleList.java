package com.algorithms.list;

/**
 * 单链表反转
 * @author DUAN
 * @date 2019/8/29
 */
public class SingleList {

    Node head;
    Node last;

    public void add(int val){
        Node l = last;
        Node newNode = new Node(val,null);
        last = newNode;
        if (l == null){
            head = newNode;
        }
        else {
            l.next = newNode;
        }
    }

    public void reverser(){
        if (head == null){
            return;
        }
        else if (head.next == null){
            return;
        }
        Node curPos = head.next;
        Node pre = head;
        Node next;
        head.next = null;
        while (curPos.next != null){
            next = curPos.next;
            curPos.next = pre;
            pre = curPos;
            curPos = next;
        }
        curPos.next = pre;
        Node currentLast = head;
        head = last;
        last = currentLast;
    }

    public void print() {
        Node h = head;
        if(h == null){
            System.out.print("[]");
            return;
        }
        while (h != null){
            System.out.print(h.val+" ");
            h = h.next;
        }
    }

    private static class Node {
        int val;
        Node next;
        Node(int val,Node next){
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args){
        SingleList singleList = new SingleList();
        for(int i = 0; i < 10; i++){
            singleList.add(i);
        }
        singleList.print();
        singleList.reverser();
        System.out.println();
        singleList.print();
    }
}
