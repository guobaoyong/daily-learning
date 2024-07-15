package javabfbcdys.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-17 11:23
 * @description 有界队列实现的Condition接口
 */
public class BoundedQueue<T> {
    private Object[] items;
    //添加的下标，删除的下标和数组当前的数量
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        this.items = new Object[size];
    }

    //添加一个元素，如果数组满，则添加线程进入等待状态，直到有空位
    public void add(T t){
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[addIndex] = t;
            if (++addIndex == items.length)
                addIndex = 0;
            ++count;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //由头部删除一个元素，如果数组为空，则删除线程进入等待状态，直到有新添加元素
    public T remove(){
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[removeIndex];
            if (++removeIndex == items.length)
                removeIndex = 0;
            --count;
            notFull.signal();
            return (T) x;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }
}
