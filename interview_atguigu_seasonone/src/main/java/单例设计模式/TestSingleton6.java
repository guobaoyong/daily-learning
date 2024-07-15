package 单例设计模式;

import java.util.concurrent.*;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 9:25
 * @Describe Singleton6测试类
 */
public class TestSingleton6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*//单线程情况下
        Singleton6 s1 = Singleton6.getInstance();
        Singleton6 s2 = Singleton6.getInstance();
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);*/

        //多线程情况下
        Callable<Singleton6> c = new Callable<Singleton6>() {
            @Override
            public Singleton6 call() throws Exception {
                return Singleton6.getInstance();
            }
        };
        //创建线程池
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton6> f1 = es.submit(c);
        Future<Singleton6> f2 = es.submit(c);

        Singleton6 s3 = f1.get();
        Singleton6 s4 =f2.get();

        System.out.println(s3 == s4);
        System.out.println(s3);
        System.out.println(s4);

        es.shutdown();

    }
}
