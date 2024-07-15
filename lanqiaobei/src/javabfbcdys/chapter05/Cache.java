package javabfbcdys.chapter05;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 8:54
 * @description 读写锁demo
 * 通过缓存实例说明读写锁的使用方式
 */
public class Cache {
    static Map<String,Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    //获取一个key对应的value
    public static final Object get(String key){
        r.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取"+key);
            return map.get(key);
        }finally {
            System.out.println(Thread.currentThread().getName()+"\t读取OK");
            r.unlock();
        }
    }

    //设置key对应的value，并返回旧的value值
    public static final Object put(String key,Object value){
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入"+key);
            return map.put(key,value);
        }finally {
            System.out.println(Thread.currentThread().getName()+"\t写入OK");
            w.unlock();
        }
    }

    //清空所有内容
    public static final void clear(){
        w.lock();
        try {
            map.clear();
        }finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                cache.put(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                cache.get(finalI +"");
            },String.valueOf(i)).start();
        }
    }
}
