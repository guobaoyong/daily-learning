package javabfbcdys.chapter06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 20:08
 * @description 阻塞队列API的使用
 * add(e) remove() element() 抛出异常
 * System.out.println(blockingQueue.add("a"));
 * System.out.println(blockingQueue.element());
 * System.out.println(blockingQueue.remove());
 *
 * offer(e) poll() peek() 特殊值
 * System.out.println(blockingQueue.offer("a"));
 * System.out.println(blockingQueue.peek());
 * System.out.println(blockingQueue.poll());
 *
 * put(e) take() 阻塞
 * blockingQueue.put("a");
 * System.out.println(blockingQueue.take());
 *
 * offer(E e, long timeout, TimeUnit unit) poll(long timeout, TimeUnit unit) 超时
 * System.out.println(blockingQueue.offer("a",5, TimeUnit.SECONDS));
 * System.out.println(blockingQueue.poll(5,TimeUnit.SECONDS));
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //抛出异常组
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

        //特殊值组
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        //阻塞
        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("-------------");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        //超时
        System.out.println(blockingQueue.offer("a",5, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b",5, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",5, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d",5, TimeUnit.SECONDS));
        System.out.println("---------------------");

        System.out.println(blockingQueue.poll(5,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(5,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(5,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(5,TimeUnit.SECONDS));


    }
}
