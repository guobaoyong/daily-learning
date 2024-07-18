package com.heima.model.article.dto;

import com.heima.model.article.entity.ApArticle;
import com.heima.model.article.enums.Flag;
import com.heima.model.common.enums.Layout;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 保存文章时的数据传输对象
 *
 * @author 高翔宇
 * @since 2024/3/5 周二 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Schema(description = "保存文章时的数据传输对象")
@NoArgsConstructor
public class ApArticleDto extends ApArticle {
    @Builder(builderMethodName = "apArticleDtoBuilder")
    public ApArticleDto(Long id, String title, Long authorId, String authorName, Long channelId, String channelName, Layout layout, Flag flag, String images, String labels, Integer likes, Integer collection, Integer comment, Integer views, String provinceId, String cityId, String countyId, LocalDateTime createdTime, LocalDateTime publishTime, Integer syncStatus, Integer origin, String staticUrl, String content) {
        super(id, title, authorId, authorName, channelId, channelName, layout, flag, images, labels, likes, collection, comment, views, provinceId, cityId, countyId, createdTime, publishTime, syncStatus, origin, staticUrl);
        this.content = content;
    }

    /**
     * 文章内容
     */
    private String content;
}
