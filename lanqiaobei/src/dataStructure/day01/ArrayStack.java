package dataStructure.day01;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-04-06 22:15
 * @Description 数组实现的栈数据结构
 */
public class ArrayStack {
    private long[] a;
    //栈数组的大小
    private int size;
    //栈顶元素索引
    private int top;

    //初始化栈
    public ArrayStack(int maxSize) {
        this.size = maxSize;
        this.a = new long[maxSize];
        this.top = -1; //表示空栈
    }

    //入栈操作
    public void push(long value){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }
        a[++top] = value;
    }

    //返回栈顶元素，不删除
    public long peek(){
        if (isEmpty()){
            System.out.println("栈为空");
            return 0;
        }
        return a[top];
    }

    //弹出栈顶元素，删除
    public long pop(){
        if (isEmpty()){
            System.out.println("栈为空");
            return 0;
        }
        return a[top--];
    }

    //获取此时栈中数量
    public int size(){
        return top+1;
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return (top == -1);
    }

    //判断栈是否已满
    public boolean isFull() {
        return (top == size -1 );
    }

    //打印栈内容
    public void display(){
        for (int i = top; i >= 0; i--) {
            System.out.println(a[i]+"   ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //创建容量为5的栈
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        System.out.println("此时栈顶内容为"+arrayStack.peek());
        arrayStack.push(3);
        arrayStack.push(2);
        arrayStack.push(1);
        System.out.println("此时栈顶内容为"+arrayStack.peek());
        arrayStack.pop();
        arrayStack.display();
    }
}
