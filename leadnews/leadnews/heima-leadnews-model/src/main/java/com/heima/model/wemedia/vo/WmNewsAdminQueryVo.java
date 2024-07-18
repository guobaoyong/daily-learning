package com.heima.model.wemedia.vo;

import com.heima.model.common.enums.Layout;
import com.heima.model.wemedia.entity.WmNews;
import com.heima.model.wemedia.enums.NewsEnable;
import com.heima.model.wemedia.enums.NewsStatus;
import lombok.*;

import java.util.Date;

/**
 * 自媒体文章查询VO
 *
 * @author 高翔宇
 * @since 2024/4/16 周二 下午2:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class WmNewsAdminQueryVo extends WmNews {
    @Builder(builderMethodName = "builder1")
    public WmNewsAdminQueryVo(Long id, Long userId, String title, String content, Layout type, Long channelId, String labels, Date createdTime, Date submittedTime, NewsStatus status, Date publishTime, String reason, Long articleId, String images, NewsEnable enable, String authorName) {
        super(id, userId, title, content, type, channelId, labels, createdTime, submittedTime, status, publishTime, reason, articleId, images, enable);
        this.authorName = authorName;
    }

    /**
     * 作者名称
     */
    private String authorName;
}


