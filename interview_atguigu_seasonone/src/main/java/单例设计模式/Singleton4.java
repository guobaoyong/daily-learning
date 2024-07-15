package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 9:20
 * @Describe 懒汉式1
 * 线程不安全（适合于单线程）
 * 延迟创建实例对象,存在线程安全问题
 *
 * （1）构造器私有化
 * （2）用一个静态变量保存这个唯一的实例
 * （3）提供一个静态方法，获取这个实例对象
 */
public class Singleton4 {
    private static Singleton4 instance;
    private Singleton4(){

    }

    public static Singleton4 getInstance(){
        if (instance == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton4();
        }
        return instance;
    }
}
