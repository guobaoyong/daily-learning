package com.heima.search.listener;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.alibaba.fastjson2.JSON;
import com.heima.common.constants.ArticleMQConstants;
import com.heima.common.constants.ESIndexConstants;
import com.heima.model.search.vo.SearchArticleVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 高翔宇
 * @since 2024/4/11 周四 上午7:47
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ArticleEsSearchSyncListener {
    private final ElasticsearchClient esClient;

    @KafkaListener(topics = ArticleMQConstants.ARTICLE_ES_SYNC_TOPIC)
    public void receiveMessage(String message) {
        System.out.println("接收到同步文章信息到ES索引库的消息：" + message);
        SearchArticleVo searchArticleVo = JSON.parseObject(message, SearchArticleVo.class);
        try {
            // 调用ES客户端，将文章信息同步到ES索引库
            esClient.index(new IndexRequest.Builder<>()
                    .index(ESIndexConstants.APP_INFO_ARTICLE)
                    .id(searchArticleVo.getId() != null ? searchArticleVo.getId().toString() : null)
                    .document(searchArticleVo)
                    .build());
        } catch (IOException | ElasticsearchException e) {
            log.error("同步文章信息到ES索引库失败", e);
        }
    }
}
