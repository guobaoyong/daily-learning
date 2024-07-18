package com.heima.search.service;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.search.entity.ApAssociateWords;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/12 周五 下午6:02
 */
public interface ApArticleAssociateSearchService {

    /**
     * 搜索联想词
     */
    ResponseResult<List<ApAssociateWords>> searchAssociateWords(@NonNull UserSearchDto userSearchDto);
}
