package qqzsh.top.contentcenter;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 21:01
 * @Description
 */
@Slf4j
@Service
public class TestService {

    @SentinelResource("common")
    public String common() {
        log.info("common....");
        return "common";
    }
}

