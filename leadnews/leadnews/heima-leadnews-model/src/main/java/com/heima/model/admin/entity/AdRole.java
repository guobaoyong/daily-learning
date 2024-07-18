package com.heima.model.admin.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色信息表
 *
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:04
 */
@Data
@Builder
public class AdRole implements Serializable {
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否启用
     */
    private Boolean isEnable;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}
