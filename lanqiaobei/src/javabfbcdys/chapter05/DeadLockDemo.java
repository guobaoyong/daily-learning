package javabfbcdys.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-17 10:48
 * @description 死锁例子
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象
 * 若无外力干涉那它们都将无法推进下去
 */
public class DeadLockDemo implements Runnable {
    private String lockA;
    private String lockB;

    public DeadLockDemo(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+"\t 尝试获得"+lockB);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockB+"\t 尝试获得"+lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadLockDemo(lockA,lockB),"AAA").start();
        new Thread(new DeadLockDemo(lockB,lockA),"BBB").start();
    }
}
