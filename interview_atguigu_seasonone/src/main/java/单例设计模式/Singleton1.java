package 单例设计模式;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 8:37
 * @Describe 直接实例化饿汉式（简介直观）
 * 直接创建实例对象的饿汉式，不管你是否需要这个对象都会创建
 *
 * （1）构造器私有化
 * （2）自行创建，并且静态变量保存
 * （3）向外提供实例
 * （4）强调这是一个单例，我们可以用final关键字修饰
 */
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();
    private Singleton1(){

    }
}
