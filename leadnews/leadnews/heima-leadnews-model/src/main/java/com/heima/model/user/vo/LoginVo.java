package com.heima.model.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * APP用户登录响应数据模型
 *
 * @author 高翔宇
 * @since 2024/2/20 周二 下午12:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "APP用户登录响应数据模型")
public class LoginVo {
    /**
     * APP用户信息
     */
    @Schema(description = "APP用户信息")
    private LoginUserInfo user;
    /**
     * APP端登录凭证
     */
    @Schema(description = "APP用户登录凭证")
    private String token;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "APP用户登录响应给前端的用户信息")
    public static class LoginUserInfo {
        /**
         * 用户id
         */
        @Schema(description = "APP用户id")
        private Long id;
        /**
         * 用户名
         */
        @Schema(description = "APP用户名称")
        private String name;
        /**
         * 手机号
         */
        @Schema(description = "APP用户手机号")
        private String phone;
    }
}
