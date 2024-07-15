package javabfbcdys.chapter02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 15:45
 * @description
 */
public class Counter {
    private AtomicInteger atomicInteger = new AtomicInteger();
    private int i = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        counter.count();
                        counter.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts){
            t.start();
        }

        //等待所以线程执行完成
        for (Thread t : ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicInteger.get());
        System.out.println(System.currentTimeMillis()-start);
    }

    /**
     * 线程安全计数器 使用CAS实现的
     */
    private void safeCount() {
        for (;;){
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i,++i);
            if (suc){
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
