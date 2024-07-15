package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-21 10:14
 * @description 线程测试题目
 * 设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。
 * 使用内部类实现线程，对j增减的时候没有考虑顺序问题
 */
public class ThreadTest01 {
    private int j;
    static CountDownLatch c = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        ThreadTest01 test01 = new ThreadTest01();
        Inc inc = test01.new Inc();
        Dec dec = test01.new Dec();
        Thread t = new Thread(inc);
        t.start();
        c.await();
        t = new Thread(dec);
        t.start();
    }

    private void inc(){
        j++;
        c.countDown();
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }

    private void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }

    class Inc implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                dec();
            }
        }
    }
}
