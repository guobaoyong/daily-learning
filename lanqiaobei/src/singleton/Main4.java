package singleton;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 11:01
 * @Describe 单例设计模式测试
 */
public class Main4 {
    String name = null;
    private Main4(){}

    private static volatile Main4 instance = null;

    public static Main4 getInstance(){
        if (instance == null){
            synchronized (Main1.class){
                if (instance == null){
                    instance = new Main4();
                }
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo(){
        System.out.println("这个实例的名字为"+name);
    }
}
