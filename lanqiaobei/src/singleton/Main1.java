package singleton;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 10:29
 * @Describe 懒汉式单例
 * 在第一次调用的时候实例化自己
 */
public class Main1 {

    private static Main1 main1 = null;
    //静态工厂方法 线程不安全
    public static Main1 getInstance(){
        if (main1 == null){
            main1 = new Main1();
        }
        return main1;
    }

    //实现线程安全有以下三个方法
    //1.在getInstance方法上加入同步(性能较差)
    public static synchronized Main1 getInstance1(){
        if (main1 == null){
            main1 = new Main1();
        }
        return main1;
    }
    //2.双重检查锁定(DCL双检查锁机制) 推荐
    public static Main1 getInstance2(){
        if (main1 == null){
            synchronized (Main1.class){
                if (main1 == null){
                    main1 = new Main1();
                }
            }
        }
        return main1;
    }
    //3.静态内部类 推荐
    private static class LazyHolder{
        private static final Main1 instance = new Main1();
    }
    private Main1(){}
    public static final Main1 getInstance3(){
        return LazyHolder.instance;
    }

}
