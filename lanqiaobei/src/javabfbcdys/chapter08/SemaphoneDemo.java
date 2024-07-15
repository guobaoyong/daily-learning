package javabfbcdys.chapter08;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 11:03
 * @description Semaphore 信号量
 * 控制并发线程的数量
 */
public class SemaphoneDemo {
    public static void main(String[] args) {
        //模拟3个停车位
        Semaphore semaphore = new Semaphore(3);

        //模拟6辆汽车
        for (int i = 0; i < 6; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    //占3s后释放
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t停车3s后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
