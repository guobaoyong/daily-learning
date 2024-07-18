package qqzsh.top.contentcenter.feignclient.interceptor;

import com.alibaba.nacos.client.utils.StringUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 19:44
 * @Description 传递token的拦截器
 */
public class TokenRelayRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 1. 获取到token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("X-Token");

        // 2. 将token传递
        if (StringUtils.isNotBlank(token)) {
            template.header("X-Token", token);
        }

    }
}

