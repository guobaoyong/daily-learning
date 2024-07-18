package com.heima.model.wemedia.dto;

import com.heima.model.common.dto.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 上午10:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "自媒体图文内容列表查询请求参数")
@ToString(callSuper = true)
public class WmNewsPageReqDto extends PageRequestDto {
    /**
     * 当前状态
     * <ul>
     *     <li>0: 草稿</li>
     *     <li>1: 提交（待审核）</li>
     *     <li>2: 审核失败</li>
     *     <li>3: 人工审核</li>
     *     <li>4: 人工审核通过</li>
     *     <li>8: 审核通过（待发布）</li>
     *     <li>9: 已发布</li>
     * </ul>
     */
    @Schema(description = "当前状态，0: 草稿，1: 提交（待审核），2: 审核失败，3: 人工审核，4: 人工审核通过，8: 审核通过（待发布），9: 已发布",
            allowableValues = {"0", "1", "2", "3", "4", "8", "9"})
    private Short status;
    /**
     * 开始时间（发布时间）
     */
    @Schema(description = "开始时间（发布时间）")
    private Date beginPubDate;
    /**
     * 结束时间（发布时间）
     */
    @Schema(description = "结束时间（发布时间）")
    private Date endPubDate;
    /**
     * 频道ID
     */
    @Schema(description = "频道ID")
    private Integer channelId;
    /**
     * 关键字
     */
    @Schema(description = "关键字")
    private String keyword;
}
