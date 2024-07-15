package xh_algorithm.chapter03;

import java.util.Arrays;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-18 17:10
 * @description 优先队列的实现
 * 基于二叉堆 小顶堆
 */
public class Main5 {

    private int[] array;
    private int size;

    public Main5(){
        //队列的初始值为32
        array = new int[32];
    }

    /**
     * 入队
     * @param key 入队元素
     */
    public void enQueue(int key){
        //队列长度超出范围，扩容
        if (size >= array.length){
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     */
    public int deQueue() throws Exception {
        if (size <=0){
            throw new Exception("the Queue is empty!");
        }
        //获取堆顶元素
        int head = array[0];
        //让最后一个元素移到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    /**
     * 上浮调整
     */
    private void upAdjust(){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        // temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]){
            //无需真正的交换，单项赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex/2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整
     */
    private void downAdjust(){
        //temp保存父节点值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex<size){
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex+1<size && array[childIndex+1]>array[childIndex]){
                childIndex++;
            }
            //如果父节点大于任何一个孩子的值，则直接跳出
            if (temp >= array[childIndex]){
                break;
            }
            //无需真正的交换，单项赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 队列扩容
     */
    private void resize(){
        //队列容量翻倍
        int newSize = this.size*2;
        this.array = Arrays.copyOf(this.array,newSize);
    }

    public static void main(String[] args) throws Exception {
        Main5 main5 = new Main5();
        main5.enQueue(3);
        main5.enQueue(5);
        main5.enQueue(10);
        main5.enQueue(2);
        main5.enQueue(7);
        System.out.println("出队元素："+main5.deQueue());
        System.out.println("出队元素："+main5.deQueue());
    }
}
