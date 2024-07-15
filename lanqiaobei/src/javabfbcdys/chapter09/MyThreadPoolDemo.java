package javabfbcdys.chapter09;

import java.util.concurrent.*;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-16 11:12
 * @description 线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 40,
                1,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        //模拟10个用户
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 办理业务");
            });
        }
        threadPool.shutdown();
    }

    public static void tthreadPoolInit(){
        //获取计算机的核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = Executors.newCachedThreadPool();

        //模拟10个用户
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 办理业务");
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        threadPool.shutdown();
    }
}
