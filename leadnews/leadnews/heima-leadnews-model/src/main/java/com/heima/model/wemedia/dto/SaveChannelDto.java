package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * 新增频道dto
 *
 * @author 高翔宇
 * @since 2024/4/14 周日 上午10:52
 */
@Data
public class SaveChannelDto {
    /**
     * 频道名称
     */
    private String name;
    /**
     * 频道描述
     */
    private String description;
    /**
     * 频道排序
     */
    private Integer ord;
    /**
     * 初始频道状态
     */
    private Boolean status;
    /**
     * 是否默认频道
     */
    private Boolean isDefault;
}
