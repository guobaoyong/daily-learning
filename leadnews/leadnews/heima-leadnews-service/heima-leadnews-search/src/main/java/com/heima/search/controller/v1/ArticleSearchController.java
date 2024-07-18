package com.heima.search.controller.v1;

import com.alibaba.fastjson2.JSONObject;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.search.service.ArticleSearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/9 周二 上午9:22
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/article/search")
public class ArticleSearchController {
    private final ArticleSearchService articleSearchService;

    /**
     * 搜索文章
     */
    @PostMapping("/search")
    @Operation(summary = "搜索文章")
    public ResponseResult<List<JSONObject>> searchArticle(@RequestBody UserSearchDto userSearchDto) throws IOException {
        log.info("搜索文章：{}", userSearchDto);
        return articleSearchService.searchArticle(userSearchDto);
    }
}
