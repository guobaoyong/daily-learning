package qqzsh.top.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 21:58
 * @Description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonseDTO {
    private Integer userId;
    /**
     * 积分
     */
    private Integer bonus;
}
