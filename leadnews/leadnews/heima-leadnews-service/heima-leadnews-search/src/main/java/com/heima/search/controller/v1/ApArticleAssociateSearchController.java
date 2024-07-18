package com.heima.search.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.search.entity.ApAssociateWords;
import com.heima.search.service.ApArticleAssociateSearchService;
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
 * @since 2024/4/12 周五 下午6:01
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/associate")
@RequiredArgsConstructor
public class ApArticleAssociateSearchController {
    private final ApArticleAssociateSearchService apArticleAssociateSearchService;

    /**
     * 搜索联想词
     */
    @PostMapping("/search")
    @Operation(summary = "搜索联想词")
    public ResponseResult<List<ApAssociateWords>> searchAssociateWords(@RequestBody UserSearchDto userSearchDto) {
        log.info("搜索联想词: {}", userSearchDto);
        return apArticleAssociateSearchService.searchAssociateWords(userSearchDto);
    }
}
