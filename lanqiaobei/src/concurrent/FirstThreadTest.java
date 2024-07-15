package concurrent;

/**
 * @author zsh
 * @site qqzsh.top
 * @company wlgzs
 * @create 2019-05-01 15:05
 * @Description 创建线程的三种方式
 * 1、继承Thread类创建线程
 * (1) 定义Thread的子类，并重写该类的run方法，该run方法的方法体就代表了线程
 * 要完成的任务。因此把run()方法称为执行体。
 * (2) 创建Thread子类的实例，即创建了该线程对象。
 * (3) 调用线程对象的start()方法来启动该线程。
 */
public class FirstThreadTest extends Thread {
    int i = 0;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            if (i == 20){
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }
}
