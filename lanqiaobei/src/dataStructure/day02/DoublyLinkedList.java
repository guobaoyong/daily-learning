package dataStructure.day02;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-08 15:57
 * @Description 双向链表
 */
public class DoublyLinkedList {
    private Node first;
    //头节点
    private Node last;
    //尾节点
    private int size;

    public DoublyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    //插入头节点
    public void insertFirst(long value) {
        Node newLink = new Node(value);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;//newLink <-- oldFirst.previous
            newLink.next = first;//newLink.next --> first
        }
        first = newLink;//first --> newLink
        size++;
    }

    //插入尾节点
    public void insertLast(long value) {
        Node newLink = new Node(value);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;//newLink <-- oldLast.next
            newLink.previous = last;//newLink.previous --> last
        }
        last = newLink;
        size++;
    }

    //删除头结点
    public Node deleteFirst() {
        if (first == null) {
            System.out.println("链表为空！");
            return null;
        }
        Node temp = first;
        //只有一个节点
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        size--;
        return temp;
    }

    //删除尾节点
    public Node deleteLast() {
        if (last == null) {
            System.out.println("链表为空");
            return null;
        }
        Node temp = last;
        //只有一个节点
        if (last.previous == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        size--;
        return temp;
    }

    //在key后面插入值为value的新节点
    public boolean insertAfter(long key, long value) {
        Node current = first;
        while (current.data != key) {
            current = current.next;
            if (current == null) {
                System.out.println("不存在值为" + value + "的节点！");
                return false;
            }
        }
        if (current == last) {
            insertLast(value);
        } else {
            Node newLink = new Node(value);
            newLink.next = current.next;
            current.next.previous = newLink;
            newLink.previous = current;
            current.next = newLink;
            size++;
        }
        return true;
    }

    //删除指定位置的节点
    public Node deleteNode(long key) {
        Node current = first;
        while (current.data != key) {
            current = current.next;
            if (current == null) {
                System.out.println("不存在该节点！");
                return null;
            }
        }
        if (current == first) {
            deleteFirst();
        } else if (current == last) {
            deleteLast();
        } else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
        }
        return current;
    }

    //从头到尾遍历链表
    public void displayForward() {
        System.out.println("List(first --> last): ");
        Node current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println(" ");
    }

    //从尾到头遍历链表
    public void displayBackward() {
        System.out.println("List(last --> first): ");
        Node current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println(" ");
    }
}

class Node {
    public long data;
    public Node next;
    public Node previous;

    public Node(long value) {
        data = value;
        next = null;
        previous = null;
    }

    public void displayLink() {
        System.out.print(data + " ");
    }
}