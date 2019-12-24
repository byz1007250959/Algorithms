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

    /**
     * 单链表反转
     */
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

    /**
     * 递归方式反转单链表
     * @author DUAN
     * @date 2019/12/24 10:22
     */
    public Node reverseList(Node head) {
        Node last = reverse(head);
        if (last != null)
            last.next = null;
        this.last = last;
        return this.head;
    }

    private Node reverse(Node head) {
        if (head == null){
            return null;
        }
        Node node = reverse(head.next);
        if (node == null) {
            this.head = head;
            return head;
        }
        node.next = head;
        return head;
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
        for(int i = 1; i <= 5; i++){
            singleList.add(i);
        }
        singleList.print();
        singleList.reverseList(singleList.head);
        singleList.print();
//        singleList.reverser();
//        System.out.println();
//        singleList.print();
    }
}
