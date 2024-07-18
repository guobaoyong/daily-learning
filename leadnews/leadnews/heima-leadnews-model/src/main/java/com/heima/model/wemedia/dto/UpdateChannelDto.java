package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * 更新频道dto
 *
 * @author 高翔宇
 * @since 2024/4/14 周日 上午11:32
 */
@Data
public class UpdateChannelDto {
    /**
     * 频道id
     */
    private Long id;
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
     * 频道状态
     */
    private Boolean status;
}
