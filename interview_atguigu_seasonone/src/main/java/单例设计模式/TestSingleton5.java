package 单例设计模式;

import java.util.concurrent.*;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 9:25
 * @Describe Singleton5测试类
 */
public class TestSingleton5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //多线程情况下
        Callable<Singleton5> c = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return Singleton5.getInstance();
            }
        };
        //创建线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton5> f1 = es.submit(c);
        Future<Singleton5> f2 = es.submit(c);

        Singleton5 s3 = f1.get();
        Singleton5 s4 =f2.get();

        System.out.println(s3 == s4);
        System.out.println(s3);
        System.out.println(s4);

        es.shutdown();

    }
}
