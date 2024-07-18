package qqzsh.top.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import qqzsh.top.contentcenter.domain.dto.user.UserDTO;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 17:22
 * @Description
 */
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {

    @GetMapping("/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);
}
