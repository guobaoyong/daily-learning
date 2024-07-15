package singleton;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 10:43
 * @Describe 饿汉式实例
 * 在类初始化的时候，已经自行实例化
 */
public class Main2 {
    private Main2(){}
    private static final Main2 MAIN_2 = new Main2();
    //静态工厂方法
    public static Main2 getInstance(){
        return MAIN_2;
    }
}
