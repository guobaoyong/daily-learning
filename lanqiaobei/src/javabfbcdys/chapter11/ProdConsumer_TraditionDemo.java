package javabfbcdys.chapter11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 21:42
 * @description
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *
 * 1 线程 操作 资源类
 * 2 判断 干活 通知
 * 3 防止虚假唤醒机制
 */

class ShareData{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        try {
            lock.lock();
            // 1 判断
            while (num != 0){
                //等待，不能生产
                condition.await();
            }
            //2 干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t "+num);

            //3 通知唤醒
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        try {
            lock.lock();
            // 1 判断
            while (num == 0){
                //等待，不能生产
                condition.await();
            }
            //2 干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t "+num);

            //3 通知唤醒
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }



}
public class ProdConsumer_TraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
    }
}
