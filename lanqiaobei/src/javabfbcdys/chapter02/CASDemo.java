package javabfbcdys.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-11 8:50
 * @description CAS 比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024)+"\t current data:"+atomicInteger.get());
    }
}
