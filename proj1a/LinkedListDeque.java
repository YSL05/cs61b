import java.util.LinkedList;

import javax.swing.JSpinner.ListEditor;

public class LinkedListDeque<T> {
    
    private class ListNode {
        public T item;
        public ListNode next;

        public ListNode(T num) 
        {
            this.item = num;
            this.next = null;
        }
    }
    T initValue = null;
    private ListNode LinkedListFirst = new ListNode(initValue);
    private ListNode LinkedListLast  = new ListNode(initValue);
    private int size = 0;
    
    public LinkedListDeque (T num)
    {
        ListNode newNode = new ListNode(num);
        LinkedListFirst.next = newNode;
        LinkedListLast.next  = newNode;
        this.size += 1;
    }
    
    public LinkedListDeque ()
    {
        LinkedListFirst.next = LinkedListLast;
        LinkedListLast.next  = LinkedListFirst;
    }

    public void addFirst(T num)
    {
        ListNode newNode = new ListNode(num);
        ListNode tempLinkedList = LinkedListFirst.next;
        LinkedListFirst.next = newNode;
        if (isEmpty()) {
            LinkedListLast.next = newNode;
        } else {
            newNode.next = tempLinkedList;
        }
        this.size += 1;
    }

    public void addLast(T num)
    {
        ListNode newNode = new ListNode(num);
        ListNode tempListNode = LinkedListLast.next;
        LinkedListLast.next = newNode;
        if (isEmpty()) {
            LinkedListFirst.next = newNode;
        } else {
            tempListNode.next = newNode;
        }
        this.size += 1;
    }

    public boolean isEmpty()
    {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size()
    {
        return this.size;
    }

    public void printDeque()
    {
        ListNode tempLinkedList = LinkedListFirst.next;
        for (int i = 0; i < this.size; i++) {
            System.out.print(tempLinkedList.item);
            System.out.print(' ');
            tempLinkedList = tempLinkedList.next;
        }
    }

    public T removeFirst()
    {
        if (isEmpty()) {
            return null;
        }
        T num = LinkedListFirst.next.item;
        LinkedListFirst.next = LinkedListFirst.next.next;
        this.size -= 1;
        return num;
    }

    public T removeLast()
    {
        if (isEmpty()) {
            return null;
        }

        T num = LinkedListLast.next.item;
        LinkedListLast.next = LinkedListLast.next.next;
        this.size -= 1;
        return num;
    }

    public T get (int index)
    {
        if (isEmpty()) {
            return null;
        }
        ListNode tempLinkedList = LinkedListFirst.next;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                return tempLinkedList.item;
            }
            tempLinkedList = tempLinkedList.next;
        }
        return null;
    }
    
    private T getRecursive(int index, ListNode linkedList) {
        if (index == 0) {
            return linkedList.item;
        }
        if (linkedList.next == null) {
            return null;
        }
        return getRecursive(index - 1, linkedList.next);
    }

    public T getRecursive(int index)
    {
        if (isEmpty()) {
            return null;
        }
        return getRecursive(index, LinkedListFirst.next);
    }
}
