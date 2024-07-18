package qqzsh.top.usercenter.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import qqzsh.top.usercenter.domain.dto.user.UserAddBonseDTO;
import qqzsh.top.usercenter.domain.entity.user.User;
import qqzsh.top.usercenter.service.user.UserService;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 21:57
 * @Description
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BonusController {
    private final UserService userService;

    @PutMapping("/add-bonus")
    public User addBonus(@RequestBody UserAddBonseDTO userAddBonseDTO) {
        Integer userId = userAddBonseDTO.getUserId();
        userService.addBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(userAddBonseDTO.getBonus())
                        .description("兑换分享...")
                        .event("BUY")
                        .build()
        );
        return this.userService.findById(userId);
    }
}

