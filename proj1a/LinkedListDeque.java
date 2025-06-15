

public class LinkedListDeque<T> {
    
    private class ListNode {
        private T item;
        private ListNode front;
        private ListNode next;

        public ListNode(T num) {
            this.item  = num;
            this.front = null;
            this.next  = null;
        }
    }
    private T initValue = null;
    private ListNode linkedList;
    private int size = 0;
    public LinkedListDeque() {
        linkedList = new ListNode(initValue);
        linkedList.front = linkedList;
        linkedList.next  = linkedList;
    }

    public void addFirst(T num) {
        ListNode newNode      = new ListNode(num);
        newNode.front         = linkedList.front;
        linkedList.front.next = newNode;
        newNode.next          = linkedList;
        linkedList.front      = newNode;
        this.size += 1;
    }

    public void addLast(T num) {
        ListNode newNode      = new ListNode(num);
        newNode.next          = linkedList.next;
        linkedList.next.front = newNode;
        newNode.front         = linkedList;
        linkedList.next       = newNode; 
        this.size += 1;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        ListNode tempLinkedList = linkedList.front;
        for (int i = 0; i < this.size; i++) {
            System.out.print(tempLinkedList.item);
            System.out.print(' ');
            tempLinkedList = tempLinkedList.front;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        ListNode tempNode   =  linkedList.front;
        linkedList.front    = tempNode.front;
        tempNode.front.next = linkedList;
        this.size -= 1;
        return tempNode.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        ListNode tempNode   =  linkedList.next;
        linkedList.next     = tempNode.next;
        tempNode.next.front = linkedList;
        this.size -= 1;
        return tempNode.item;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        ListNode tempListNode = linkedList.front;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                return tempListNode.item;
            }
            tempListNode = tempListNode.front;
        }
        return null;
    }
    
    private T getRecursive(int index, ListNode tempLinkedList) {
        if (index == 0) {
            return tempLinkedList.item;
        }
        if (tempLinkedList.front == linkedList) {
            return null;
        }
        return getRecursive(index - 1, tempLinkedList.front);
    }

    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        return getRecursive(index, linkedList.front);
    }
}
