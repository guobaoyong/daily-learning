package qqzsh.top.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 16:54
 * @Description Feign配置类
 * 不能加@Configuration
 */
public class GlobalFeignConfiguration {

    @Bean
    public Logger.Level level(){
        //让feign打印请求的所有细节
        return Logger.Level.FULL;
    }
}
