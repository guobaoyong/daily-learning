package cxydmmszn.chapter01;

import java.util.Stack;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-23 17:30
 * @description 设计一个有getMin功能的栈
 */
public class MyStack1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum){
        if (this.stackMin.isEmpty()){
            this.stackMin.push(newNum);
        }else if (newNum <= this.getmin()){
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop(){
        if (this.stackData.isEmpty()){
            throw new RuntimeException("Your stack is empty");
        }
        int value = this.stackData.pop();
        if (value == this.getmin()){
            this.stackMin.pop();
        }
        return value;
    }

    public int getmin() {
        if (this.stackMin.isEmpty()){
            throw new RuntimeException("Your stack is empty");
        }
        return this.stackMin.peek();
    }
}
