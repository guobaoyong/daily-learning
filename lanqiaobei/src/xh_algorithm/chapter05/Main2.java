package xh_algorithm.chapter05;

import java.util.Stack;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-09-16 16:14
 * @description 最小栈的实现
 * 实现一个栈，该栈带有入栈、出栈、取最小元素三个方法，且时间复杂度都为O(1)
 */
public class Main2 {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<> ();

    /**
     * 入栈操作
     * @param eleement
     */
    void push(int eleement) {
        mainStack.push(eleement);
        //如果辅助栈为空，或者新元素小于或等于辅助栈栈顶，则将新元素压入辅助栈
        if (minStack.isEmpty() || eleement <= minStack.peek()){
            minStack.push(eleement);
        }
    }

    /**
     * 出栈操作
     * @return
     */
    Integer pop(){
        //如果出栈元素和辅助栈栈顶元素相等，辅助栈出栈
        if (mainStack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        return mainStack.pop();
    }

    /**
     * 获取栈最小元素
     * @return
     */
    int getMin(){
        if (mainStack.empty()){
            try {
                throw new Exception("stack is null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        Main2 stack = new Main2();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }

}
