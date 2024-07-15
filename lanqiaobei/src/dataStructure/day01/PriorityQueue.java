package dataStructure.day01;

import sun.net.www.protocol.http.ntlm.NTLMAuthentication;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-08 10:47
 * @Description 堆实现的队列
 */
public class PriorityQueue {
    private long[] a;
    //队列大小
    private int size;
    //元素个数
    private int nItems;
    public PriorityQueue(int maxSize){
        this.size = maxSize;
        a = new long[size];
        nItems = 0;
    }

    //插入操作
    public void insert(long value){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        int j;
        if (nItems == 0){
            a[nItems++] = value;
        }else {
            for (j = nItems-1; j >= 0 ; j--) {
                if (value > a[j]){
                    a[j+1]=a[j];
                }else {
                    break;
                }
            }
            a[j+1]=value;
            nItems++;
        }
    }

    //移除操作
    public long remove(){
        if (isEmpty()){
            System.out.println("队列为空");
            return 0;
        }
        return a[--nItems];
    }

    //获取最小值
    public long peekMin(){
        return a[nItems-1];
    }

    //此时队列的元素个数
    public int size(){
        return nItems;
    }

    public void display(){
        for (int i = nItems-1; i >= 0 ; i--) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    //判断是否为空
    public boolean isEmpty() {
        return (nItems == 0);
    }

    //判断队列是否已满
    public boolean isFull() {
        return (nItems == size);
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(4);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.remove();
        queue.insert(4);
        queue.insert(5);
        queue.remove();
        queue.display();
    }
}
