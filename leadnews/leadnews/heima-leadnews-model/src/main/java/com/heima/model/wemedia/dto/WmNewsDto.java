package com.heima.model.wemedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 自媒体保存或修改文章的数据传输模型
 *
 * @author 高翔宇
 * @since 2024/2/22 周四 下午3:51
 */
@Data
@Schema(description = "自媒体保存或修改文章的数据传输模型")
public class WmNewsDto {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 频道ID
     */
    private Long channelId;

    /**
     * 标签
     */
    private String labels;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 图文内容
     */
    private String content;

    /**
     * 文章类型
     * <ul>
     *     <li>0: 无图文章</li>
     *     <li>1: 单图文章</li>
     *     <li>3: 多图文章</li>
     *     <li>-1: 自动识别文章类型</li>
     * </ul>
     */
    private Short type;

    /**
     * 文章状态
     * <ul>
     *     <li>0: 草稿</li>
     *     <li>1: 待审核</li>
     * </ul>
     */
    private Short status;

    /**
     * 封面图片列表
     */
    private List<String> images;
}
