package com.heima.model.wemedia.dto;

import lombok.Data;

/**
 * 频道分页查询dto
 *
 * @author 高翔宇
 * @since 2024/4/14 周日 上午10:29
 */
@Data
public class ChannelPageQueryDto {
    /**
     * 频道名称
     */
    private String name;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示条数
     */
    private Integer size;
}
