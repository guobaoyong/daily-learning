package synchronizedTest;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-07 21:46
 * @Describe 消失的数解决方案·4种
 */
public class DisappearRequest6 implements Runnable {

    static DisappearRequest6 instance = new DisappearRequest6();
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        //让线程进入等待状态
        t1.join();
        t2.join();
        System.out.println(i);
    }

    //方法1
    /*@Override
    public synchronized void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }*/

    /*//方法2
    @Override
    public void run() {
        synchronized (DisappearRequest6.class){
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }*/

    /*//方法3
    @Override
    public void run() {
        synchronized (this){
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }*/

    //方法4
    @Override
    public void run() {
        method();
    }

    public static synchronized void method(){
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }
}
