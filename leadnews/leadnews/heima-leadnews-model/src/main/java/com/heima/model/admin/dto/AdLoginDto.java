package com.heima.model.admin.dto;

import lombok.Data;

/**
 * 登录dto
 *
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:33
 */
@Data
public class AdLoginDto {
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
}
