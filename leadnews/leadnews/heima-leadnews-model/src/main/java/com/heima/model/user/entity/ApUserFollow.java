package com.heima.model.user.entity;

import com.heima.model.user.enums.ApUserFollowLevel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * App用户关注信息表
 *
 * @author 高翔宇
 * @since 2024/4/18 周四 上午9:17
 */
@Data
public class ApUserFollow {
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 关注的用户ID
     */
    private Long followId;
    /**
     * 关注的作者名称
     */
    private String followName;
    /**
     * 关注度
     * <ul>
     *     <li>0: 偶尔感兴趣</li>
     *     <li>1: 一般</li>
     *     <li>2: 经常</li>
     *     <li>3: 高度</li>
     * </ul>
     */
    private ApUserFollowLevel level;
    /**
     * 是否动态通知
     */
    private Boolean isNotice;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
}
