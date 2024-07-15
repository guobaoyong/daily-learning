package concurrent;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-04 9:59
 * @description 创建线程的三种方式
 * 2、实现Runnable接口创建线程类
 * (1) 定义runnable接口的实现类，并重写该接口的run方法，该run方法的方法体同样
 * 是该线程的执行体。
 * (2) 创建Runnable实现类的实例，并依次实例作为Thread得到target来创建Thread对象，
 * 该Thread对象才是真正的线程对象。
 * (3) 调用线程对象的start()方法来启动线程。
 */
public class RunnableThreadTest implements  Runnable{
    private int i;

    @Override
    public void run() {
        for (i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            if (i == 20){
                RunnableThreadTest rtt = new RunnableThreadTest();
                new Thread(rtt,"新线程1").start();
                new Thread(rtt,"新线程2").start();
            }
        }
    }
}
