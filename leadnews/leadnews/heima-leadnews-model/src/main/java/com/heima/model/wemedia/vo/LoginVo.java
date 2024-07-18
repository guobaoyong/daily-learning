package com.heima.model.wemedia.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自媒体用户登录返回结果
 *
 * @author 高翔宇
 * @since 2024/2/20 周二 下午3:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "自媒体用户登录返回结果")
public class LoginVo {
    /**
     * 自媒体用户信息
     */
    @Schema(description = "自媒体用户信息")
    private LoginUserInfo user;
    /**
     * 登录凭证
     */
    @Schema(description = "自媒体用户登录凭证")
    private String token;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "自媒体用户登录响应给前端的用户信息")
    public static class LoginUserInfo {
        @Schema(description = "自媒体用户id")
        private Long id;
        @Schema(description = "自媒体用户名称")
        private String name;
        @Schema(description = "自媒体用户头像")
        private String phone;
    }
}
