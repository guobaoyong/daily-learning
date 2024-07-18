package com.heima.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleConfigMapper;
import com.heima.article.service.ApArticleConfigService;
import com.heima.model.article.entity.ApArticleConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 高翔宇
 * @since 2024/3/5 周二 16:04
 */
@Service
@AllArgsConstructor
public class ApArticleConfigServiceImpl extends ServiceImpl<ApArticleConfigMapper, ApArticleConfig> implements ApArticleConfigService {
    ApArticleConfigMapper apArticleConfigMapper;

    /**
     * 根据map更新，规定map中必须包含articleId；如果是布尔类型的字段，map中的值为1则为true，0则为false
     */
    @Override
    public void updateByMapByArticleId(Map<String, String> map) {
        String articleId = "articleId";
        if (!map.containsKey(articleId) || map.get(articleId) == null) {
            throw new RuntimeException("缺少文章id");
        }
        String isComment = "isComment";
        String isForward = "isForward";
        String isDown = "enable";
        String isDelete = "isDelete";
        ApArticleConfig apArticleConfig = apArticleConfigMapper.selectOne(new LambdaUpdateWrapper<ApArticleConfig>().eq(ApArticleConfig::getArticleId, Long.valueOf(map.get(articleId))));
        if (apArticleConfig == null) {
            throw new RuntimeException("文章配置不存在");
        }
        if (map.containsKey(isComment)) {
            apArticleConfig.setIsComment(getBoolean(map.get(isComment)));
        }
        if (map.containsKey(isForward)) {
            apArticleConfig.setIsForward(getBoolean(map.get(isForward)));
        }
        if (map.containsKey(isDown)) {
            apArticleConfig.setIsDown(!getBoolean(map.get(isDown)));
        }
        if (map.containsKey(isDelete)) {
            apArticleConfig.setIsDelete(!getBoolean(map.get(isDelete)));
        }
        apArticleConfigMapper.updateById(apArticleConfig);
    }

    private boolean getBoolean(String value) {
        return "1".equals(value) || 1 == Integer.parseInt(value);
    }
}