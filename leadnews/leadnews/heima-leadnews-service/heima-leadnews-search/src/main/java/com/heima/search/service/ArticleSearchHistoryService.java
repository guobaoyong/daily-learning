package com.heima.search.service;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.HistorySearchDto;
import com.heima.search.entity.ApUserSearch;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/12 周五 上午11:35
 */
public interface ArticleSearchHistoryService {
    /**
     * 保存搜索历史
     */
    void save(@NonNull String keyword, @NonNull Long userId);

    /**
     * 加载搜索历史
     */
    ResponseResult<List<ApUserSearch>> load();

    /**
     * 删除搜索历史
     */
    ResponseResult<?> delete(HistorySearchDto historySearchDto);
}
