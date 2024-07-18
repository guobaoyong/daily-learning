package com.heima.model.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.heima.model.user.enums.ApUserRealnameStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:22
 */
@Data
@Builder
public class ApUserRealname implements Serializable {

    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 身份证号
     */
    private String idno;
    /**
     * 正面照片
     */
    private String fontImage;
    /**
     * 反面照片
     */
    private String backImage;
    /**
     * 手持照片
     */
    private String holdImage;
    /**
     * 活体照片
     */
    private String liveImage;
    /**
     * 实名认证状态
     */
    private ApUserRealnameStatus status;
    /**
     * 拒绝原因
     */
    private String reason;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 提交时间
     */
    @TableField("submited_time")
    private LocalDateTime submittedTime;
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
