package synchronizedTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-08 22:11
 * @Describe
 */
public class SynchronizedToLock13 {

    Lock lock = new ReentrantLock();

    public synchronized void method1(){
        System.out.println("我是synchronized形式的锁");
    }

    public void method2(){
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock13 synchronizedToLock13 = new SynchronizedToLock13();
        synchronizedToLock13.method1();
        synchronizedToLock13.method2();
    }

}
