package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 8:48
 * @Describe Singleton2测试类
 */
public class TestSingleton2 {
    public static void main(String[] args) {
        Singleton2 s = Singleton2.INSTANCE;
        System.out.println(s);
    }
}
