package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-04 10:35
 * @description 创建线程的三种方式
 * 3、通过Callable和Future创建线程
 * (1) 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，
 * 并且有返回值。
 * (2) 创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask
 * 对象封装了该Callable对象的call()方法的返回值。
 * (3) 使用FutureTask对象作为Thread对象的target创建并启动新线程。
 * (4) 调用FutureTask对象的get()方法来获得子线程执行结束后的返回值。
 */
public class CallableThreadTest implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (;i<100;i++){
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }

    public static void main(String[] args) {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值为;"+i);
            if (i == 20){
                new Thread(ft,"有返回的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值为"+ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
