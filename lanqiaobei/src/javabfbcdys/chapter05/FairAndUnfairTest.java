package javabfbcdys.chapter05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-12 9:35
 * @description 公平锁和非公平锁的区别
 * 公平锁保证了锁的获取按照FIFO原则，而代价是进行大量的线程切换。
 * 非公平锁虽然可能造成线程"饥饿"，但极少的线程切换，保证了其更大的吞吐量
 */
public class FairAndUnfairTest {
    private static Lock fairlock = new ReentrantLock2(true);
    private static Lock unfairlock = new ReentrantLock2(false);
    private static CountDownLatch start;

    public void fair(){
        testLock(fairlock);
    }

    public void unfair() {
        testLock(unfairlock);
    }

    private void testLock(Lock lock) {
        start = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Job(lock);
            thread.setName("" + i);
            thread.start();
        }
        start.countDown();
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 2; i++) {
                lock.lock();
                try {
                    System.out.println("Lock by [" + getName() + "], Waiting by " + ((ReentrantLock2) lock).getQueuedThreads());
                } finally {
                    lock.unlock();
                }
            }
        }

        public String toString() {
            return getName();
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        private static final long serialVersionUID = -6736727496956351588L;

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    public static void main(String[] args) {
        FairAndUnfairTest fairAndUnfairTest = new FairAndUnfairTest();
        fairAndUnfairTest.fair();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        fairAndUnfairTest.unfair();
    }
}
