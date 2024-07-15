package javabfbcdys.chapter01;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-09 8:08
 * @description 多线程一定快么？
 * 不一定。当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢
 * 因为线程有创建和上下文切换的开销
 */
public class ConcurrencyTest {
    private static final long count = 10000l;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis()-start;
        thread.join();
        System.out.println("并发执行结果concurrency:"+time+"ms,b="+b);
    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("串行执行结果concurrency:"+time+"ms,b="+b);
    }
}
