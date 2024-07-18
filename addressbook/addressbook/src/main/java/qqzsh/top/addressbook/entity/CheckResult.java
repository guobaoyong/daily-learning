package qqzsh.top.addressbook.entity;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-16 16:42
 * @Description jwt验证信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckResult {

    private int errCode;

    private boolean success;

    private Claims claims;

}

