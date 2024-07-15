package qqzsh.top.bingimages.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsh
 * @site https://www.qqzsh.top
 * @create 2020-03-27 9:36
 * @Description
 */
@Configuration
public class StorageProperties {

    @Value("${zipPath}")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
