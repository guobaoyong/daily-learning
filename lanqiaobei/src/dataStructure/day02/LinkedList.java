package dataStructure.day02;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-08 15:11
 * @Description 单链表
 */
public class LinkedList {
    private Link first;
    //节点数
    private int nElem;

    public LinkedList() {
        first = null;
        nElem = 0;
    }

    //添加头节点
    public void insertFirst(int value){
        Link newList = new Link(value);
        newList.next = first;
        first = newList;
        nElem++;
    }

    //删除头节点
    public Link deleteFirst(){
        if (isEmpty()){
            System.out.println("链表为空");
            return null;
        }
        Link temp = first;
        first = first.next;
        nElem--;
        return temp;
    }

    /************************************************************
     ***下面是有序链表的插入***
     ***这样简单排序就可以用链表来实现，复杂度为O(N)
     ***定义一个方法，传入一个数组，
     ***在方法内，遍历数组并调用insert方法就可以将数组中的数据排好序
     ***********************************************************/
    public void insert(int value){
        Link newList = new Link(value);
        Link previous = null;
        Link current = first;
        while (current != null && value > current.data){
            previous = current;
            current = current.next;
        }
        if (previous == null){
            first = newList;
        }else {
            previous.next = newList;
        }
        newList.next = current;
        nElem++;
    }

    //查找特定的节点
    public Link find(int value){
        Link current = first;
        while (current.data != value){
            if (current.next == null){
                return null;
            }else {
                current = current.next;
            }
        }
        return current;
    }

    //删除特定的节点
    public Link delete(int value){
        Link current = first;
        Link previous = first;
        while (current.data != value){
            if (current.next == null){
                return null;
            }
            previous = current;
            current = current.next;
        }
        if (current == first){
            first = first.next;
        }else {
            previous.next = current.next;
        }
        nElem--;
        return current;
    }

    //打印链表
    public void display(){
        if (isEmpty()){
            System.out.println("链表为空");
            return;
        }
        Link current = first;
        while (current != null){
            current.displayLink();
            current = current.next;
        }
        System.out.println(" ");
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return (first == null);
    }

    //获取链表的节点数目
    public int size(){
        return nElem;
    }
}

class Link{
    public int data;
    public Link next;

    public Link(int data) {
        this.data = data;
        this.next = null;
    }

    public void displayLink(){
        System.out.print("{"+data+"}");
    }
}
