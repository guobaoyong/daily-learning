package javabfbcdys.chapter02;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-09 10:51
 * @description volatile可见性demo
 */

class MyData{
    volatile int number = 0;

    public void add(){
        this.number = 60;
    }

    public void addPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * 原子性增加1
     */
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        /**
         * volatile不能保证原子性
         */
        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(myData.number);
        System.out.println(myData.atomicInteger);

        System.exit(0);

        /**
         * volatile可以保证可见性
         */
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                //让线程休眠3s
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println(Thread.currentThread().getName()+"\t update value "+myData.number);
        },"AAA").start();

        while (myData.number == 0){
            //main线程一直在等待，直至number不再为0
        }

        System.out.println(Thread.currentThread().getName()+"\t over,main"+myData.number);
    }
}
