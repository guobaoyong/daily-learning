package 单例设计模式;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 9:25
 * @Describe Singleton4测试类
 */
public class TestSingleton4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*//单线程情况下
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);*/

        //多线程情况下
        Callable<Singleton4> c = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };
        //创建线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = es.submit(c);
        Future<Singleton4> f2 = es.submit(c);

        Singleton4 s3 = f1.get();
        Singleton4 s4 =f2.get();

        System.out.println(s3 == s4);
        System.out.println(s3);
        System.out.println(s4);

        es.shutdown();

    }
}
