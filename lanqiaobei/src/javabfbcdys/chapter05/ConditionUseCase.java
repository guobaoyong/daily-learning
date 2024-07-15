package javabfbcdys.chapter05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-17 11:14
 * @description Condition接口
 */
public class ConditionUseCase {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait(){
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal(){
        lock.lock();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
