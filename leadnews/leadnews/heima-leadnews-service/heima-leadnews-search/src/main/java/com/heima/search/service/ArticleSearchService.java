package com.heima.search.service;

import com.alibaba.fastjson2.JSONObject;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;

import java.io.IOException;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/9 周二 上午9:23
 */
public interface ArticleSearchService {
    /**
     * 搜索文章
     */
    ResponseResult<List<JSONObject>> searchArticle(UserSearchDto userSearchDto) throws IOException;
}
