package javabfbcdys.chapter06;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 22:05
 * @description
 * 题目: synchronized和lock有什么区别？用新的lock有什么好处，举例说明
 * 1 原始构成
 * synchronized是Java关键字属于JVM层面，
 *      monitorenter(底层通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象，
 *      只有在同步代码块中才能调用wait/notify等方法)
 *      monitorexit
 * lock是具体实现类(java.util.concurrent.locks.Lock)是api层面的
 *
 * 2 使用方法
 *      synchronized 不需要用户手动去释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 *      ReentrantLock 则需要用户去手动释放锁，若没有主动释放锁，就有可能出现死锁的现象
 *
 * 3 等待是否可中断
 *      synchronized 不可中断，除非抛出异常或者正常运行完成
 *      ReentrantLock 可以中断，1 设置超时方法 tryLock(long timeout , TimeUtil unit)
 *                              2 lockInterruptibly()放在代码块中，调用interrupt()方法可中断
 *
 * 4 加锁是否公平
 *      synchronized 非公平锁
 *      ReentrantLock 二者都可以，默认非公平锁，构造方法可以传入boolean值，true为公平锁
 *
 * 5 锁绑定多个条件Condition
 *      synchronized 没有
 *      ReentrantLock 用来实现分组唤醒，需要唤醒线程可以精准唤醒，而不是像synchronized那样，要不随机唤醒一个，要么全部唤醒
 *
 *
 * 题目：多线程之间顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，B打印10次，C打印15次
 * 紧接着
 * AA打印5次，B打印10次，C打印15次
 * 。。。。。。
 * 来10轮
 */

class ShareResource{
    private int num = 1; //A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        try {
            lock.lock();
            //1 判断
            while (num != 1){
                c1.await();
            }
            //2 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+ i);
            }
            //3 通知
            num = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        try {
            lock.lock();
            //1 判断
            while (num != 2){
                c2.await();
            }
            //2 干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+ i);
            }
            //3 通知
            num = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        try {
            lock.lock();
            //1 判断
            while (num != 3){
                c3.await();
            }
            //2 干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+ i);
            }
            //3 通知
            num = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}


public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();

        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}
