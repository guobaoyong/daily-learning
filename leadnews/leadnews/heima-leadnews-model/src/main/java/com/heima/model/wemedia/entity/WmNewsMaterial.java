package com.heima.model.wemedia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.heima.model.wemedia.enums.NewsMaterialRelationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 下午5:07
 */
@Data
@Schema(description = "自媒体图文内容和素材关联表数据模型")
@TableName("wm_news_material")
@Builder
public class WmNewsMaterial {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 素材ID
     */
    private Integer materialId;
    /**
     * 图文ID
     */
    private Long newsId;
    /**
     * 类型
     * <ul>
     *     <li>0: 内容引用</li>
     *     <li>1: 封面</li>
     * </ul>
     */
    private NewsMaterialRelationType type;
    /**
     * 排序
     */
    private Integer ord;
}
