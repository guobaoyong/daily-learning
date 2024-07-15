package javabfbcdys.chapter11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-15 8:33
 * @description
 *
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 *
 */

class MyResource{
    //默认开启，进行生产+消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd(){
        String data = null;
        boolean retValue;
        while (FLAG){
            try {
                data = atomicInteger.incrementAndGet()+"";
                retValue = blockingQueue.offer(data,2, TimeUnit.SECONDS);
                if (retValue){
                    System.out.println(Thread.currentThread().getName()+"\t 插入队列" + data + "成功");
                }else {
                    System.out.println(Thread.currentThread().getName()+"\t 插入队列" + data + "失败");
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停,FLAG="+FLAG+"，生产结束");
    }

    public void myConsumer() {
        String result = null;
        while (FLAG) {
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "超过2s没有取到数据，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列result="+result+"成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}

public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            myResource.myProd();
        },"Prod").start();

        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            System.out.println();
            System.out.println();
            myResource.myConsumer();
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5s之后，叫停，活动结束");

        myResource.stop();

    }
}
