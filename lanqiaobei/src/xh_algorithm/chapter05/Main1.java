package xh_algorithm.chapter05;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-09-16 15:46
 * @description 如何判断链表有环
 * 追击相遇问题
 */
public class Main1 {

    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    static boolean isCycle(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                return true;
            }
        }
        return false;
    }

    /**
     * 链表有环，求环长
     * @param head
     * @return
     */
    static int cycleLength(Node head){
        Node p1 = head;
        Node p2 = head;
        int length = 0;
        int c = 0;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                c++;
                if (c == 2){
                    break;
                }
            }
            if (c == 1){
                length++;
            }
        }
        return length;
    }


    /**
     * 入环节点
     * @param head
     * @return
     */
    static Node inCycle(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                //p1回到起点
                p1 = head;
                while (p2 != null && p2.next != null){
                    p1 = p1.next;
                    p2 = p2.next;
                    if (p1 == p2){
                        return p1;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        Node node6 = new Node(8);
        Node node7 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node4;

        System.out.println(isCycle(node1));
        System.out.println(cycleLength(node1));
        System.out.println(inCycle(node1).data);
    }

}
