package javabfbcdys.chapter09;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-15 17:21
 * @description 多线程中，第3种获取多线程的方法
 * Callable<V></>
 */

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("*********come in Callable");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());

        Thread t1 = new Thread(futureTask,"AA");

        t1.start();

        //建议放到最后
        int result = futureTask.get();

        System.out.println(result+100);
    }

}
