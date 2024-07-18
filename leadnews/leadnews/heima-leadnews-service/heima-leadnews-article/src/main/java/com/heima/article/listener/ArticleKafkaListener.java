package com.heima.article.listener;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heima.article.service.ApArticleConfigService;
import com.heima.common.constants.WmNewsMQConstants;
import com.heima.model.article.entity.ApArticleConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author 高翔宇
 * @since 2024/4/3 周三 下午5:10
 */
@Component
@AllArgsConstructor
@Slf4j
@Transactional
public class ArticleKafkaListener {
    private final ApArticleConfigService apArticleConfigService;

    /**
     * 监听文章上下架消息
     */
    @KafkaListener(topics = WmNewsMQConstants.WM_NEWS_UP_OR_DOWN_TOPIC)
    public void handleUpOrDown(String message) {
        log.info("收到文章上下架消息：{}", message);
        HashMap<String, String> map = JSONObject.parseObject(message, new TypeReference<>() {
        });
        if (map.containsKey("articleId") && map.containsKey("enable")) {
            apArticleConfigService.updateByMapByArticleId(map);
        } else {
            log.error("收到文章上下架消息，但是没有找到文章id或enable字段。希望传递的json字符串是一个Map，包含的key有：articleId和enable");
            throw new RuntimeException("收到文章上下架消息，但是没有找到文章id或enable字段");
        }
    }

    /**
     * 监听文章删除消息
     */
    @KafkaListener(topics = WmNewsMQConstants.WM_NEWS_DELETE_TOPIC)
    public void handleDelete(String message) {
        log.info("收到文章删除消息：{}", message);
        HashMap<String, String> map = JSONObject.parseObject(message, new TypeReference<>() {
        });
        if (map.containsKey("articleId") || map.get("articleId") != null) {
            String articleId = map.get("articleId");
            ApArticleConfig articleConfig = apArticleConfigService.getOne(new LambdaQueryWrapper<ApArticleConfig>().eq(ApArticleConfig::getArticleId, articleId));
            if (articleConfig == null) {
                log.error("收到文章删除消息，但是没有找到文章id对应的文章配置信息。articleId:{}", articleId);
                throw new RuntimeException("收到文章删除消息，但是没有找到文章id对应的文章配置信息");
            } else if (!articleConfig.getIsDown()) {
                log.error("收到文章删除消息，但是文章id对应的文章配置信息enable字段为true，不能删除。articleId:{}", articleId);
                throw new RuntimeException("收到文章删除消息，但是文章id对应的文章配置信息enable字段为true，不能删除");
            } else if (articleConfig.getIsDelete()) {
                log.warn("收到文章删除消息，但是文章id对应的文章配置信息isDelete字段为true，不能重复删除。articleId:{}", articleId);
            }
            articleConfig.setIsDelete(true);
            apArticleConfigService.updateById(articleConfig);
        } else {
            log.error("收到文章删除消息，但是没有找到文章id字段。希望传递的json字符串是一个Map，包含的key有：articleId");
            throw new RuntimeException("收到文章删除消息，但是没有找到文章id字段");
        }
    }
}
