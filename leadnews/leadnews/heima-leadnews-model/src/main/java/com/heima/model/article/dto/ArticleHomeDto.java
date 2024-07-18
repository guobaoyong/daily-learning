package com.heima.model.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024/2/10 周六 下午8:55
 */
@Data
@Schema(description = "文章首页数据传输对象")
public class ArticleHomeDto {
    /**
     * 最大时间
     */
    @Schema(description = "最大时间", nullable = true)
    private Date maxBehotTime;

    /**
     * 最小时间
     */
    @Schema(description = "最小时间", nullable = true)
    private Date minBehotTime;

    /**
     * 分页大小
     */
    @Schema(description = "分页大小。若超过50，后台会处理成50，后台默认为20", nullable = true)
    Integer size;

    /**
     * 频道ID
     */
    @Schema(description = "频道ID，默认为查询全部", nullable = true)
    String tag;
}
