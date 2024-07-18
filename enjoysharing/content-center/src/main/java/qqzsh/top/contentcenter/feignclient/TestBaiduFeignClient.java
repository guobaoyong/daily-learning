package qqzsh.top.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 17:29
 * @Description feign脱离ribbon的使用
 */
@FeignClient(name = "baidu", url = "http://www.baidu.com")
public interface TestBaiduFeignClient {

    @GetMapping("")
    String index();
}

