package javabfbcdys.chapter05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-13 21:32
 * @description 自旋锁
 * 题目;实现一个自旋锁
 * 自旋锁的好处：循环比较直到成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5s，B随后进来发现
 * 当前线程持有锁，不是null，所以只能通过自旋锁等待，直到A释放锁后B抢到
 */
public class SpinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"\t come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void unMyLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread+"\t invoke unlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLockDemo.myLock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spinLockDemo.unMyLock();
            }
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLockDemo.myLock();
                spinLockDemo.unMyLock();
            }
        },"B").start();


    }
}
