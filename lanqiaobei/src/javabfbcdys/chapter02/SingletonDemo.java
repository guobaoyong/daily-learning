package javabfbcdys.chapter02;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-10 22:13
 * @description 单例模式的线程安全实现
 * volatile实现
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t我是构造方法");
    }

    //DCL(Double check lock 双端检锁机制)
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        for (int i = 0; i < 10000; i++) {
            new Thread(() ->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
