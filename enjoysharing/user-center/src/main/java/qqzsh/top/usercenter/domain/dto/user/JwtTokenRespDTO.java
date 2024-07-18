package qqzsh.top.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 17:39
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtTokenRespDTO {
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Long expirationTime;
}
