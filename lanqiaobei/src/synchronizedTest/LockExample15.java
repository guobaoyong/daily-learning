package synchronizedTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-09 8:47
 * @Describe 展示Lock的方法
 */
public class LockExample15 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //加锁
        lock.lock();
        //释放锁
        lock.unlock();
        //尝试获得锁
        lock.tryLock();
        try {
            //尝试获得锁，设置超时时间
            lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
