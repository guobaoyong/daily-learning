package com.heima.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.json.JsonData;
import com.alibaba.fastjson2.JSONObject;
import com.heima.common.constants.ESIndexConstants;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.model.user.entity.ApUser;
import com.heima.search.service.ArticleSearchHistoryService;
import com.heima.search.service.ArticleSearchService;
import com.heima.search.thread.ApUserThreadLocalUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author 高翔宇
 * @since 2024/4/9 周二 上午9:23
 */
@Service
@AllArgsConstructor
public class ArticleSearchServiceImpl implements ArticleSearchService {
    private final ElasticsearchClient esClient;
    private final ArticleSearchHistoryService articleSearchHistoryService;

    /**
     * 搜索文章
     */
    @Override
    public ResponseResult<List<JSONObject>> searchArticle(UserSearchDto userSearchDto) throws IOException {
        // 检查参数
        if (userSearchDto == null || !StringUtils.hasLength(userSearchDto.getSearchWords())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 关键词
        String searchWord = userSearchDto.getSearchWords();
        ApUser apUser = ApUserThreadLocalUtil.getApUser();
        /*
        * userSearchDto.getFromIndex() == 0：这个判断是检查 userSearchDto.getFromIndex() 的值是否为 0。
        * userSearchDto 是用户搜索的数据传输对象，getFromIndex() 方法获取的是用户希望从哪个索引开始搜索。
        * 如果 getFromIndex() 的值为 0，那么说明用户希望从第一个索引开始搜索，也就是说这是一个新的搜索请求，而不是翻页或者滚动加载更多结果。
        * */
        if (apUser != null && userSearchDto.getFromIndex() == 0) {
            articleSearchHistoryService.save(searchWord, apUser.getId());
        }
        // 字段名称
        String fieldNameOfPublishTime = "publishTime";
        String fieldNameOfTitle = "title";
        String fieldNameOfContent = "content";
        // 设置查询条件
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(ESIndexConstants.APP_INFO_ARTICLE)
                .query(b -> b.bool(b1 -> b1
                        .must(b2 -> b2
                                .match(new MatchQuery.Builder()
                                        .field(fieldNameOfTitle)
                                        .field(fieldNameOfContent)
                                        .query(searchWord)
                                        .build()))
                        .filter(b2 -> b2
                                .range(new RangeQuery.Builder()
                                        .field(fieldNameOfPublishTime)
                                        .lt(JsonData.of(userSearchDto.getMinBehotTime() != null ? userSearchDto.getMinBehotTime() : System.currentTimeMillis()))
                                        .build()))))
                .from(userSearchDto.getFromIndex())
                .size(userSearchDto.getPageSize())
                .sort(b -> b.field(b1 -> b1.field(fieldNameOfPublishTime).order(SortOrder.Desc)))
                .highlight(b -> b.highlightQuery(b1 -> b1.match(new MatchQuery.Builder()
                                .field(fieldNameOfTitle)
                                .query(searchWord)
                                .build()))
                        .fields(Map.of(fieldNameOfTitle, new HighlightField.Builder().build()))
                        .preTags("<font style='color: red; font-size: inherit'>")
                        .postTags("</font>"))
                .build();
        return new ResponseResult<>(200, "搜索成功~", esClient.search(searchRequest, (Type) Map.class).hits().hits().stream().map(objectHit -> {
            JSONObject sourceJsonObject = JSONObject.from(objectHit.source());
            Map<String, List<String>> highlightSource = objectHit.highlight();
            // 搜索返回的结果集添加一个属性，h_title，值为搜索关键词（通过CSS）高亮后的标题
            if (!highlightSource.isEmpty()) {
                sourceJsonObject.put("h_title", StringUtils.collectionToCommaDelimitedString(highlightSource.get(fieldNameOfTitle)));
            }
            return sourceJsonObject;
        }).toList());
    }
}
