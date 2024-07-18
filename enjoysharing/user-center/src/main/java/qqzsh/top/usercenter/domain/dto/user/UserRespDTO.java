package qqzsh.top.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 17:40
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRespDTO {
    /**
     * id
     */
    private Integer id;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;
    /**
     * 微信昵称
     */
    private String wxNickname;
}

