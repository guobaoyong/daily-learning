package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 8:46
 * @Describe Singleton1测试类
 */
public class TestSingleton1 {
    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);
    }
}
