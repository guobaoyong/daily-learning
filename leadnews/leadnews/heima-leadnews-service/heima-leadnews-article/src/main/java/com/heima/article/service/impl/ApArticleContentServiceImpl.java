package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.service.ApArticleContentService;
import com.heima.model.article.entity.ApArticleContent;
import org.springframework.stereotype.Service;

/**
 * @author 高翔宇
 * @since 2024/2/14 周三 下午5:55
 */
@Service
public class ApArticleContentServiceImpl extends ServiceImpl<ApArticleContentMapper, ApArticleContent>
        implements ApArticleContentService {
}
