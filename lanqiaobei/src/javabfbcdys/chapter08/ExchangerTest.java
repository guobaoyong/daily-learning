package javabfbcdys.chapter08;

import java.util.concurrent.*;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 11:20
 * @description Exchanger 线程间交换数据
 */
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水A";
                //超时时间为5s
                try {
                    exgr.exchange(A, 5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B";
                try {
                    String A = exgr.exchange(B,5,TimeUnit.SECONDS);
                    System.out.println("A和B的数据是否一致"+A.equals(B)+
                            ",A录入的是"+A+",B录入的是"+B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }
}
