package singleton;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 11:05
 * @Describe 单例设计模式测试主类
 */
public class Main5 {
    public static void main(String[] args) {
        Main4 main4 = Main4.getInstance();
        main4.setName("123");
        Main4 main41 = Main4.getInstance();
        main41.setName("456");

        main4.printInfo();
        main41.printInfo();

        if (main4 == main41){
            System.out.println("创建的是同一个实例");
        }else {
            System.out.println("创建的不是同一个实例");
        }
    }
}
