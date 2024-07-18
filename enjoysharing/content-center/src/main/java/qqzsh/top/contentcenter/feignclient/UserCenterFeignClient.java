package qqzsh.top.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import qqzsh.top.contentcenter.domain.dto.user.UserAddBonseDTO;
import qqzsh.top.contentcenter.domain.dto.user.UserDTO;
import qqzsh.top.contentcenter.feignclient.fallbackfactory.UserCenterFeignClientFallbackFactory;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 16:42
 * @Description
 */
@FeignClient(name = "user-center",
//        fallback = UserCenterFeignClientFallback.class,
          fallbackFactory = UserCenterFeignClientFallbackFactory.class)
//@FeignClient(name = "user-center", configuration = UserCenterFeignConfiguration.class)
public interface UserCenterFeignClient {

    /**
     * http://user-center/users/{id}
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);

    @PutMapping("/users/add-bonus")
    UserDTO addBonus(@RequestBody UserAddBonseDTO userAddBonseDTO);

}
