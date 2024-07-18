package com.heima.model.wemedia.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 上传图片素材返回结果
 *
 * @author 高翔宇
 * @since 2024/2/20 周二 下午2:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "上传图片素材返回结果")
public class UploadPictureVo {
    /**
     * 素材id
     */
    @Schema(description = "素材id")
    private Integer id;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;
    /**
     * 图片地址
     */
    @Schema(description = "图片地址")
    private String url;
    /**
     * 类型
     */
    @Schema(description = "类型")
    private Short type;
    /**
     * 是否收藏
     */
    @Schema(description = "是否收藏，0：未收藏，1：已收藏")
    private Short isCollection;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createdTime;
}
