package qqzsh.top.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 17:42
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserLoginDTO {
    /**
     * code
     */
    private String code;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 微信昵称
     */
    private String wxNickname;
}
