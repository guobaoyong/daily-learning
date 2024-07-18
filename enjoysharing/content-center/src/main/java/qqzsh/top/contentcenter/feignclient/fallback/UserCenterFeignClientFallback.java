package qqzsh.top.contentcenter.feignclient.fallback;

import org.springframework.stereotype.Component;
import qqzsh.top.contentcenter.domain.dto.user.UserAddBonseDTO;
import qqzsh.top.contentcenter.domain.dto.user.UserDTO;
import qqzsh.top.contentcenter.feignclient.UserCenterFeignClient;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-09 22:09
 * @Description
 */
@Component
public class UserCenterFeignClientFallback implements UserCenterFeignClient {

    @Override
    public UserDTO findById(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setWxNickname("流控/降级返回的用户");
        return userDTO;
    }

    @Override
    public UserDTO addBonus(UserAddBonseDTO userAddBonseDTO) {
        return null;
    }
}
