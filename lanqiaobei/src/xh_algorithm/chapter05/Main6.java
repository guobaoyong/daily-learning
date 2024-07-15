package xh_algorithm.chapter05;

import java.util.Stack;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-11-06 11:34
 * @description 栈实现队列
 */
public class Main6 {

    private Stack<Integer> stackA = new Stack<>();
    private Stack<Integer> stackB = new Stack<>();

    /**
     * 入队操作
     * @param element 入队的元素
     */
    public void enQueue(int element){
        stackA.push(element);
    }

    /**
     * 出队操作
     * @return
     */
    public Integer deQueue(){
        if (stackB.isEmpty()){
            if (stackA.isEmpty()){
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }

    /**
     * 栈A元素转移到栈B
     */
    private void transfer(){
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) {
        Main6 main6 = new Main6();
        main6.enQueue(1);
        main6.enQueue(2);
        main6.enQueue(3);
        System.out.println(main6.deQueue());
        System.out.println(main6.deQueue());
        main6.enQueue(4);
        System.out.println(main6.deQueue());
        System.out.println(main6.deQueue());
    }

}
