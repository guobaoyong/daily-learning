package test;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-07-31 14:40
 * @description
 * 一般方法的引用格式:
 *
 * 如果是静态方法，则是ClassName::methodName。如 Object ::equals
 * 如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;
 * 构造函数.则是ClassName::new
 */
public class Demo2 {

    public static void main(String[] args) {
        /*
         * 方法引用
         */
        Runnable runnable = Demo2::run;
        new Thread(runnable).start();

    }

    public static void run(){
        System.out.println("方法引用的代码...");
    }
}
