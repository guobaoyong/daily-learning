package com.heima.model.admin.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色权限信息表
 *
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:07
 */
@Data
@Builder
public class AdRoleAuth {
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 资源类型
     */
    private Short type;
    /**
     * 资源id
     */
    private Integer entryId;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}
