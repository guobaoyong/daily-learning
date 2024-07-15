package dataStructure.day01;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-08 9:51
 * @Description 循环数组实现的队列
 */
public class RoundQueue {
    private long[] a;
    //数组大小
    private int size;
    //实际存储容量
    private int nItems;
    //头
    private int front;
    //尾
    private int rear;

    public RoundQueue(int maxSize) {
        this.size = maxSize;
        a = new long[size];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    //插入操作
    public void insert(long value){
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        rear = ++rear % size;
        a[rear] = value;
        nItems++;
    }

    //移除操作
    public long remove(){
        if (isEmpty()){
            System.out.println("队列为空");
            return 0;
        }
        nItems--;
        front = front % size;
        return a[front++];
    }

    //打印队列
    public void display(){
        if (isEmpty()){
            System.out.println("队列为空");
            return ;
        }
        int item = front;
        for (int i = 0; i < nItems; i++) {
            System.out.print(a[item++ % size] + "  ");
        }
        System.out.println("");
    }

    //返回队列头元素
    public long peek(){
        if (isEmpty()){
            System.out.println("队列为空");
            return 0;
        }
        return a[front];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return (nItems == 0);
    }

    //判断队列是否已满
    public boolean isFull() {
        return (nItems == size);
    }

    //获取此时队列中的元素个数
    public int size(){
        return nItems;
    }

    public static void main(String[] args) {
        //初始化一个队列
        RoundQueue queue = new RoundQueue(4);
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
