package com.heima.search.service.impl;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.search.entity.ApAssociateWords;
import com.heima.search.service.ApArticleAssociateSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/12 周五 下午6:04
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApArticleAssociateSearchServiceImpl implements ApArticleAssociateSearchService {
    private final MongoTemplate mongoTemplate;

    /**
     * 搜索联想词
     */
    @Override
    public ResponseResult<List<ApAssociateWords>> searchAssociateWords(@NonNull UserSearchDto userSearchDto) {
        if (userSearchDto.getSearchWords() == null) {
            return ResponseResult.errorResult(200, "搜索词不能为空！");
        }
        // 限制搜索页数
        int pageSize = userSearchDto.getPageSize();
        if (pageSize > 20) {
            pageSize = 20;
        }
        List<ApAssociateWords> apAssociateWords = mongoTemplate.find(Query.query(Criteria
                        .where("associateWords")
                        .regex(".*?\\" + userSearchDto.getSearchWords() + ".*"))
                .limit(pageSize), ApAssociateWords.class);
        log.info("搜索到的联想词: {}", apAssociateWords);
        return ResponseResult.okResult(apAssociateWords);
    }
}
