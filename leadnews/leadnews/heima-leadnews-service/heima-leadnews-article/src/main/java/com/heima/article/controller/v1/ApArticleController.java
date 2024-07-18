package com.heima.article.controller.v1;

import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleHomeLoadConstants;
import com.heima.model.article.entity.ApArticle;
import com.heima.model.article.dto.ApArticleDto;
import com.heima.model.article.dto.ArticleHomeDto;
import com.heima.model.common.dto.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/10 周六 下午8:50
 */
@RestController
@RequestMapping(value = "/api/v1/article")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "APP文章系统接口", description = "APP文章系统接口")
@SecurityRequirement(name = "token")
public class ApArticleController {
    private final ApArticleService apArticleService;

    /**
     * 加载首页
     *
     * @param articleHomeDto 加载参数，包括最大时间，最小时间，加载数量，频道ID
     * @return 加载结果，载荷：文章列表
     */
    @PostMapping(value = "/load")
    @Operation(summary = "APP加载首页")
    public ResponseResult<List<ApArticle>> load(@RequestBody ArticleHomeDto articleHomeDto) {
        log.info("加载首页：{}", articleHomeDto);
        return apArticleService.load(articleHomeDto, null);
    }

    /**
     * 加载更多
     *
     * @param articleHomeDto 加载参数，包括最大时间，最小时间，加载数量，频道ID
     * @return 加载结果，载荷：文章列表
     */
    @PostMapping(value = "/loadmore")
    @Operation(summary = "APP加载更多")
    public ResponseResult<List<ApArticle>> loadMore(@RequestBody ArticleHomeDto articleHomeDto) {
        log.info("加载更多：{}", articleHomeDto);
        return apArticleService.load(articleHomeDto, ArticleHomeLoadConstants.LOAD_TYPE_IS_LOAD_MORE);
    }

    /**
     * 加载最新
     *
     * @param articleHomeDto 加载参数，包括最大时间，最小时间，加载数量，频道ID
     * @return 加载结果，载荷：文章列表
     */
    @PostMapping(value = "/loadnew")
    @Operation(summary = "APP加载最新")
    public ResponseResult<List<ApArticle>> loadNew(@RequestBody ArticleHomeDto articleHomeDto) {
        log.info("加载最新：{}", articleHomeDto);
        return apArticleService.load(articleHomeDto, ArticleHomeLoadConstants.LOAD_TYPE_IS_LOAD_NEW);
    }

    /**
     * 保存文章，响应结果中携带文章ID
     */
    @Operation(summary = "保存文章，响应结果中携带文章ID")
    @PostMapping(value = "/save")
    public ResponseResult<Long> saveArticle(@RequestBody ApArticleDto apArticleDto) {
        log.info("保存文章：{}", apArticleDto);
        return apArticleService.saveArticle(apArticleDto);
    }
}
