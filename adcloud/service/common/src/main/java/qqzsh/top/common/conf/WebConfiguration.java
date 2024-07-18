package qqzsh.top.common.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-19 21:01
 * @Description 统一配置-消息转换器
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 自定义HTTP消息转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>>
                                                   converters) {
        //清空默认
        converters.clear();
        //加入自定义的转换器；作用是将Java对象转换为json对象
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
