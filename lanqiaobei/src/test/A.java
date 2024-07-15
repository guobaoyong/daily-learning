package test;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-31 14:35
 * @description
 */
//添加此注解后，接口中只能有一个方法。
@FunctionalInterface
public interface A {
    void call();

    default void fun() {
        System.out.println("我是接口的默认方法1中的代码");
    }

    default void fun2() {
        System.out.println("我是接口的默认方法2中的代码");
    }
}
