package 单例设计模式;

import java.io.*;
import java.util.Properties;

/**
 * @author zsh
 * @company wlgzs
 * @create 2019-03-26 8:50
 * @Describe 静态代码块饿汉式（适合复杂实例化）
 *
 */
public class Singleton3 {
    public static final Singleton3 INSTANCE;
    private String info;

    //饿汉式，直接创建对象，不存在线程安全问题
    static {
        Properties properties = new Properties();
        try {
            String path = System.getProperty("user.dir")+"/src/main/resources/singleton.properties";
            properties.load(new InputStreamReader(new FileInputStream(path)));
            INSTANCE = new Singleton3(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }

    private Singleton3(String info){
        this.info = info;
    }
}
