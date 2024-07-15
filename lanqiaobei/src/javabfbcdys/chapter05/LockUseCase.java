package javabfbcdys.chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 20:43
 * @description 公平锁和非公平锁
 */
public class LockUseCase {
    public static void main(String[] args) {
        //非公平锁.默认
        //对于synchronized也是非公平的锁
        Lock lock = new ReentrantLock(false);
        Lock lock1 = new ReentrantLock(true);
        lock.lock();
        try {

        }finally {
            lock.unlock();
        }
    }
}
