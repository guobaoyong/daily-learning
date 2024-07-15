package xh_algorithm.chapter02;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-13 15:34
 * @description 单链表的简单操作
 */
public class Main3 {
    //头节点指针
    private Node head;
    //尾节点指针
    private Node last;
    //链表的实际长度
    private int size;

    /**
     * 链表插入元素
     * @param data 插入元素
     * @param index 插入位置
     */
    public void insert(int data,int index){
        if (index<0 || index>size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertedNode = new Node(data);
        if (size == 0){
            //空链表
            head = insertedNode;
            last = insertedNode;
        }else if (index == 0){
            //插入头部
            insertedNode.next = head;
            head = insertedNode;
        }else if (size == index){
            //插入尾部
            last.next = insertedNode;
            last = insertedNode;
        }else {
            //插入中间
            Node prevNode = get(index -1);
            insertedNode.next = prevNode.next;
            prevNode.next = insertedNode;
        }
        size++;
    }

    /**
     * 链表删除元素
     * @param index 删除的位置
     * @return
     */
    public Node remove(int index){
        if (index <0 || index>=size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node removedNode = null;
        if (index == 0){
            //删除头节点
            removedNode = head;
            head = head.next;
        }else if (index == size){
            //删除尾节点
            Node prevNode = get(index-1);
            removedNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        }else {
            //删除中间节点
            Node prevNode = get(index -1);
            Node nextNode = prevNode.next.next;
            removedNode = prevNode.next;
            prevNode.next = nextNode;
        }
        size--;
        return removedNode;
    }

    /**
     * 链表查找元素
     * @param index 查找的位置
     * @return
     */
    public Node get(int index){
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出链表
     */
    public void output(){
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Main3 main3 = new Main3();
        main3.insert(3,0);
        main3.insert(7,1);
        main3.insert(9,2);
        main3.insert(5,3);
        main3.insert(6,1);
        main3.remove(0);
        main3.output();
    }

}

/**
 * 链表节点
 */
class Node{
    int data;
    Node next;
    Node(int data){
        this.data=data;
    }
}