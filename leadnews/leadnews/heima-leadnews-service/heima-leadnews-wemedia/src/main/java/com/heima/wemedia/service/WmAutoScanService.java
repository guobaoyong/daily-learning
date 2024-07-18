package com.heima.wemedia.service;

/**
 * @author 高翔宇
 * @since 2024/3/6 周三 12:41
 */
public interface WmAutoScanService {
    /**
     * 审核文章
     *
     * @param wmNewsId 文章id
     */
    void reviewNesById(Long wmNewsId);
}
