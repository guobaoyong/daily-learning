package javabfbcdys.chapter07;

import sun.misc.Unsafe;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-23 8:09
 * @description
 */
public class AtomicInteger {

    private volatile int value;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset = 0;

    public final int get() {
        return value;
    }

    public final int getAndIncrement(){
        for (;;){
            int current = get();
            int next = current + 1;
            if (compareAndSet(current,next)){
                return current;
            }
        }
    }

    public final boolean compareAndSet(int expect,int update){
        return unsafe.compareAndSwapInt(this,valueOffset,expect,update);
    }
}
