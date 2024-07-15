package javabfbcdys.chapter05;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-13 21:55
 * @description 读写锁
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源，就不应该再有其他线程可以对资源进行读或写
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 *
 * 写;原子+独占
 */

//资源类
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入"+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入OK");
        }finally {
            rwlock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取"+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取OK"+result);
        }finally {
            rwlock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.put(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.get(finalI +"");
            },String.valueOf(i)).start();
        }
    }
}
