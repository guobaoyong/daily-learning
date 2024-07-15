package test;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-02-25 9:02
 * @Describe 多线程方法wait() and notify() 方法测试
 * https://www.cnblogs.com/zsh-blogs/p/10429184.html
 */
public class WaitNotifyTest {

    // 在多线程间共享的对象上使用wait
    private String[] shareObj = {"true"};

    //线程等待
    class ThreadWait extends Thread{

        public ThreadWait(String name){
            super(name);
        }

        @Override
        public void run() {
            synchronized (shareObj){
                while ("true".equals(shareObj[0])){
                    System.out.println("线程"+this.getName()+"开始等待");
                    long startTime = System.currentTimeMillis();
                    try {
                        shareObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println("线程"+this.getName()+"等待的时间为"+
                            (endTime - startTime));
                }
            }
            System.out.println("线程" + this.getName() + "等待结束");
        }
    }

    //线程唤醒
    class ThreadNotify extends Thread{

        public ThreadNotify(String name){
            super(name);
        }

        @Override
        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (shareObj){
                System.out.println("线程" + this.getName() + "开始准备通知");
                shareObj[0] = "false";
                shareObj.notifyAll();
                System.out.println("线程" + this.getName() + "通知结束");
            }
            System.out.println("线程" + this.getName() + "运行结束");
        }
    }

    public static void main(String[] args) {
        WaitNotifyTest waitNotifyTest = new WaitNotifyTest();
        ThreadWait threadWait1 = waitNotifyTest.new ThreadWait("wait thread1");
        /**
         * 优先级   : 只能反映线程的 重要程度 或者是 紧急程度 , 不能决定 是否一定先执行
         * setPriority()
         * 1~10   1最低  10最高    5是默认值
         */
        threadWait1.setPriority(2);
        ThreadWait threadWait2 = waitNotifyTest.new ThreadWait("wait thread2");
        threadWait2.setPriority(3);
        ThreadWait threadWait3 = waitNotifyTest.new ThreadWait("wait thread3");
        threadWait3.setPriority(4);

        ThreadNotify threadNotify = waitNotifyTest.new ThreadNotify("notify thread");

        threadNotify.start();
        threadWait1.start();
        threadWait2.start();
        threadWait3.start();
    }


}
