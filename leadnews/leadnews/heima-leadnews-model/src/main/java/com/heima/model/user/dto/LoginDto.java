package com.heima.model.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/2/7 周三 12:26
 */
@Data
@Schema(description = "用户登录时传递的请求体")
public class LoginDto {
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
}
