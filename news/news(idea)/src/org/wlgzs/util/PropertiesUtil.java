package org.wlgzs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-11-07 21:25
 * @Describe 根据key获取配置文件的value
 */
public class PropertiesUtil {
    public static String getValue(String key){
        Properties properties = new Properties();
        InputStream resourceAsStream = new PropertiesUtil().getClass().getResourceAsStream("/news.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
