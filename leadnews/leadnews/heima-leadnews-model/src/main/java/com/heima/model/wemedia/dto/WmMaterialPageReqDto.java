package com.heima.model.wemedia.dto;

import com.heima.model.common.dto.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 高翔宇
 * @since 2024/2/18 周日 下午5:07
 */
@Schema(description = "查询素材列表数据传输对象")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class WmMaterialPageReqDto extends PageRequestDto {
    /**
     * 是否是收藏的素材
     * <ul>
     *     <li>0: 否</li>
     *     <li>1: 是</li>
     * </ul>
     */
    @Schema(description = "是否是收藏的素材，0: 否，1: 是")
    private short isCollection;
}

