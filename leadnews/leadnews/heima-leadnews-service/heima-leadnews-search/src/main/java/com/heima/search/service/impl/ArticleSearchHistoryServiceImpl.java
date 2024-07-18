package com.heima.search.service.impl;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dto.HistorySearchDto;
import com.heima.model.user.entity.ApUser;
import com.heima.search.entity.ApUserSearch;
import com.heima.search.service.ArticleSearchHistoryService;
import com.heima.search.thread.ApUserThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/12 周五 上午11:35
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleSearchHistoryServiceImpl implements ArticleSearchHistoryService {
    private final MongoTemplate mongoTemplate;

    /**
     * 保存搜索历史
     *
     * @param keyword 搜索词
     * @param userId  用户ID
     */
    @Override
    @Async
    public void save(@NotNull String keyword, @NotNull Long userId) {
        log.info("保存搜索历史。keyword: {}, userId: {}", keyword, userId);
        // 尝试查询搜索词是否已经存在，如果存在，则更新时间，如果不存在，则插入
        ApUserSearch item = mongoTemplate.findOne(Query.query(Criteria
                .where("userId")
                .is(userId)
                .and("keyword")
                .is(keyword)), ApUserSearch.class);
        if (item != null) {
            item.setCreatedTime(new Date());
            mongoTemplate.save(item);
        } else {
            // 查询所有记录，根据时间排序，如果超过10条，则删除最早的一条
            List<ApUserSearch> items = mongoTemplate.find(Query
                    .query(Criteria
                            .where("userId")
                            .is(userId))
                    .with(Sort.by(Sort.Direction.DESC, "createdTime")), ApUserSearch.class);
            ApUserSearch newItem = ApUserSearch.builder()
                    .userId(userId)
                    .keyword(keyword)
                    .createdTime(new Date())
                    .build();
            if (items.isEmpty() || items.size() < 10) {
                // 若搜索历史为空或者不足10条，则直接插入
                mongoTemplate.save(newItem);
            } else {
                // 若搜索历史超过10条，将最早的一条替换为新的搜索词
                mongoTemplate.findAndReplace(Query.query(Criteria
                        .where("userId")
                        .is(items.get(items.size() - 1).getUserId())), newItem);
            }
        }
    }

    /**
     * 加载搜索历史
     */
    @Override
    public ResponseResult<List<ApUserSearch>> load() {
        ApUser apUser = ApUserThreadLocalUtil.getApUser();
        if (apUser == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        List<ApUserSearch> apUserSearchList = mongoTemplate.find(Query
                .query(Criteria
                        .where("userId")
                        .is(apUser.getId()))
                .with(Sort.by(Sort.Direction.DESC, "createdTime")), ApUserSearch.class);
        log.info("查询到的搜索历史：{}", apUserSearchList);
        return ResponseResult.okResult(apUserSearchList);
    }

    /**
     * 删除搜索历史
     */
    @Override
    public ResponseResult<?> delete(HistorySearchDto historySearchDto) {
        if (historySearchDto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApUser user = ApUserThreadLocalUtil.getApUser();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        long deletedCount = mongoTemplate.remove(Query.query(Criteria
                .where("userId")
                .is(user.getId())
                .and("id")
                .in(historySearchDto.getId())), ApUserSearch.class).getDeletedCount();
        return ResponseResult.okResult(200, "删除成功，共删除" + deletedCount + "条记录");
    }
}
