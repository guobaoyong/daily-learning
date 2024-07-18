package qqzsh.top.contentcenter.feignclient.fallbackfactory;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import qqzsh.top.contentcenter.domain.dto.user.UserAddBonseDTO;
import qqzsh.top.contentcenter.domain.dto.user.UserDTO;
import qqzsh.top.contentcenter.feignclient.UserCenterFeignClient;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 22:12
 * @Description
 */
@Component
@Slf4j
public class UserCenterFeignClientFallbackFactory implements FallbackFactory<UserCenterFeignClient> {

    @Override
    public UserCenterFeignClient create(Throwable cause) {
        return new UserCenterFeignClient() {
            @Override
            public UserDTO findById(Integer id) {
                log.warn("远程调用被限流/降级了", cause);
                UserDTO userDTO = new UserDTO();
                userDTO.setWxNickname("流控/降级返回的用户");
                return userDTO;
            }

            @Override
            public UserDTO addBonus(UserAddBonseDTO userAddBonseDTO) {
                log.warn("远程调用被限流/降级了", cause);
                return null;
            }
        };
    }
}