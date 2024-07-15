package singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-02 10:49
 * @Describe 登记式单例
 * 类似String里面的方法，将类名注册，下次从里面直接获取
 */
public class Main3 {
    private static Map<String,Main3> map = new HashMap<>();
    //初始数据
    static {
        Main3 main3 = new Main3();
        map.put(main3.getClass().getName(),main3);
    }
    protected Main3(){}
    //静态工厂方法，返还此类的唯一实例
    public static Main3 getInstance(String name){
        if (name == null){
            name = Main3.class.getName();
            System.out.println("Name == null,"+"--> name =" + name);
        }
        if (map.get(name) == null){
            try {
                map.put(name, (Main3) Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return map.get(name);
    }

    public String print(){
        return "Hello";
    }

    public static void main(String[] args) {
        Main3 instance = Main3.getInstance(null);
        System.out.println(instance.print());
    }
}
