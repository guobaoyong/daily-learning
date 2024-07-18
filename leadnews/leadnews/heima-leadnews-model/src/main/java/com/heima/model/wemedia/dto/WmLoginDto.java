package com.heima.model.wemedia.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 自媒体用户登录数据传输对象
 *
 * @author itheima，高翔宇
 * @since 2024-02-15, 周四, 16:0
 */
@Schema(description = "自媒体用户登录数据传输对象")
@Data
public class WmLoginDto {

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String name;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
}
