package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 9:02
 * @Describe Singleton3测试类
 */
public class TestSingleton3 {
    public static void main(String[] args) {
        Singleton3 s = Singleton3.INSTANCE;
        System.out.println(s);
    }
}
