package javabfbcdys.chapter02;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-10 17:21
 * @description 指令重排demo
 */
public class ResortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01(){
        a = 1;
        flag = true;
    }

    public void method02(){
        if (flag){
            a = a + 5;
            System.out.println(Thread.currentThread().getName()+"---retValue："+a);
        }
    }

    public static void main(String[] args) {
        ResortSeqDemo resortSeqDemo = new ResortSeqDemo();
        for (int i = 0; i < 20000; i++) {
            new Thread(() ->{
                resortSeqDemo.method01();
                resortSeqDemo.method02();
            },"AAA").start();

            new Thread(() ->{
                resortSeqDemo.method01();
                resortSeqDemo.method02();
            },"BBB").start();
        }
    }
}
