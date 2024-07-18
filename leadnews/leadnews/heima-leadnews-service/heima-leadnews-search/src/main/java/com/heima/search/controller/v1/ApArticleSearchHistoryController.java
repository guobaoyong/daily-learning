package com.heima.search.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.HistorySearchDto;
import com.heima.search.entity.ApUserSearch;
import com.heima.search.service.ArticleSearchHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/12 周五 下午5:13
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class ApArticleSearchHistoryController {
    private final ArticleSearchHistoryService articleSearchHistoryService;

    /**
     * 加载搜索历史
     */
    @PostMapping("/load")
    @Operation(summary = "加载搜索历史")
    public ResponseResult<List<ApUserSearch>> load() {
        log.info("加载搜索历史");
        return articleSearchHistoryService.load();
    }

    /**
     * 删除搜索历史
     */
    @PostMapping("/del")
    @Operation(summary = "删除搜索历史")
    public ResponseResult<?> delete(@RequestBody HistorySearchDto historySearchDto) {
        log.info("删除搜索历史：{}", historySearchDto);
        return articleSearchHistoryService.delete(historySearchDto);
    }
}
