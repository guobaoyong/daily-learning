package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 10:42
 * @Describe 懒汉式3
 * 静态内部类形式（适合多线程）
 * 在内部类被加载和初始化时，才创建INSTANCE对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的。
 * 因为实在内部类加载和初始化时，创建的，因此线程是安全的
 */
public class Singleton6 {

    private Singleton6(){

    }

    private static class Inner{
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance(){
        return Inner.INSTANCE;
    }
}
