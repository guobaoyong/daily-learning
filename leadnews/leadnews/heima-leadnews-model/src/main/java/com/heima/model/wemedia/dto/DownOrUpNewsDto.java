package com.heima.model.wemedia.dto;

import com.heima.model.wemedia.entity.WmNews;
import com.heima.model.wemedia.enums.NewsEnable;
import lombok.Data;

/**
 * 文章下线或上线数据传输对象
 *
 * @author 高翔宇
 * @since 2024/4/3 周三 下午3:02
 */
@Data
public class DownOrUpNewsDto {
    /**
     * 文章id：{@link WmNews#getId()}
     */
    private Long id;
    /**
     * 是否可用
     * <ul>
     *     <li>0: 不可用</li>
     *     <li>1: 可用</li>
     * </ul>
     * 建议使用{@link NewsEnable#of(short)}方法获取枚举对象
     */
    private Short enable;
}
