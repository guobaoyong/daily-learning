package com.heima.model.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 高翔宇
 * @since 2024-02-6, 周二, 18:7
 */
@Data
@Slf4j
@Schema(description = "分页请求数据传输对象")
public class PageRequestDto {

    /**
     * 分页大小
     */
    @Schema(description = "分页大小")
    protected Integer size;

    /**
     * 当前页
     */
    @Schema(description = "当前页")
    protected Integer page;

    /**
     * 检查参数，如果参数不合法，设置默认值。
     * <ul>
     *     <li>page: 默认值为1</li>
     *     <li>size: 默认值为10</li>
     * </ul>
     */
    public void checkParam() {
        if (this.page == null || this.page < 0) {
            setPage(1);
        }
        if (this.size == null || this.size < 0 || this.size > 100) {
            setSize(10);
        }
    }
}
