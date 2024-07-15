package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 9:43
 * @Describe 懒汉式2
 * 线程安全（适合于多线程）
 */
public class Singleton5 {
    private static Singleton5 instance;
    private Singleton5(){

    }

    public static Singleton5 getInstance(){
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
