package qqzsh.top.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 17:41
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRespDTO {
    /**
     * token
     */
    private JwtTokenRespDTO token;

    /**
     * 用户信息
     */
    private UserRespDTO user;
}
