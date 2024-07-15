package xh_algorithm.chapter02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-13 16:49
 * @description 循环队列的实现
 */
public class Main4 {

    private int[] array;
    //头部下标
    private int front;
    //尾部下标
    private int rear;

    public Main4(int capacity){
        this.array = new int[capacity];
    }

    /**
     * 入队
     * @param element 入队的元素
     * @throws Exception
     */
    public void enQueue(int element) throws Exception {
        if ((rear+1)%array.length == front){
            throw new Exception("队列已满");
        }
        array[rear] = element;
        rear = (rear+1)%array.length;
    }

    /**
     * 入队
     * @return
     * @throws Exception
     */
    public int deQueue() throws Exception {
        if (rear == front){
            throw new Exception("队列已满");
        }
        int deQueueElement = array[front];
        front = (front+1)%array.length;
        return deQueueElement;
    }

    /**
     * 输出队列
     */
    public void output(){
        for (int i = front; i != rear ; i = (i+1)%array.length) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        Main4 main4 = new Main4(6);
        main4.enQueue(3);
        main4.enQueue(5);
        main4.enQueue(6);
        main4.enQueue(8);
        main4.enQueue(1);
        main4.deQueue();
        main4.deQueue();
        main4.deQueue();
        main4.enQueue(2);
        main4.enQueue(4);
        main4.enQueue(9);
        main4.output();
        Map<String,String> map = new HashMap<>();
    }
}
