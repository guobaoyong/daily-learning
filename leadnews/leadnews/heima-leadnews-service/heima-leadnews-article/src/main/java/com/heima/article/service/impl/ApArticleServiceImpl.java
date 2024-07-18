package com.heima.article.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleConfigMapper;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleHomeLoadConstants;
import com.heima.common.constants.ArticleMQConstants;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.entity.ApArticle;
import com.heima.model.article.entity.ApArticleConfig;
import com.heima.model.article.entity.ApArticleContent;
import com.heima.model.article.dto.ApArticleDto;
import com.heima.model.article.dto.ArticleHomeDto;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.search.vo.SearchArticleVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 高翔宇
 * @since 2024/2/11 周日 下午2:51
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    private final ApArticleMapper apArticleMapper;
    private final ApArticleConfigMapper apArticleConfigMapper;
    private final ApArticleContentMapper apArticleContentMapper;
    private final Configuration configuration;
    private final FileStorageService fileStorageService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 加载首页文章
     *
     * @param type 加载类型 1加载更多 2加载最新
     */
    @Override
    public ResponseResult<List<ApArticle>> load(ArticleHomeDto articleHomeDto, Short type) {
        Date maxBehotTime = articleHomeDto.getMaxBehotTime();
        if (maxBehotTime == null) {
            maxBehotTime = new Date();
        }
        // 获取最小时间
        Date minBehotTime = articleHomeDto.getMinBehotTime();
        if (minBehotTime == null) {
            minBehotTime = new Date();
        }
        // 获取要查询的文章数量
        Integer size = articleHomeDto.getSize();
        if (size == null || size <= 0) {
            size = ArticleHomeLoadConstants.DEFAULT_LOAD_SIZE;
        } else {
            size = Math.min(size, ArticleHomeLoadConstants.MAX_LOAD_SIZE);
        }
        // 获取要查询的频道ID
        String tag = articleHomeDto.getTag();
        if (tag == null) {
            tag = ArticleHomeLoadConstants.DEFAULT_CHANNEL_ID;
        }
        // 获取加载类型，允许为空，如果不为空，只允许1或2
        if (type != null && (type == 1 || type == 2)) {
            type = ArticleHomeLoadConstants.LOAD_TYPE_IS_LOAD_MORE;
        }
        // 查询文章列表
        List<ApArticle> articles = apArticleMapper.loadArticleList(
                maxBehotTime, minBehotTime, size, tag, type);
        // 返回结果
        return ResponseResult.okResult(articles);
    }

    /**
     * 保存文章，响应结果中携带文章ID
     */
    @Override
    public ResponseResult<Long> saveArticle(ApArticleDto apArticleDto) {
        if (apArticleDto == null) {
            return ResponseResult.errorResult(501, "文章数据为空");
        } else if (!StringUtils.hasLength(apArticleDto.getContent())) {
            throw new RuntimeException("文章内容不能为空");
        }
        ApArticle article = new ApArticle();
        BeanUtils.copyProperties(apArticleDto, article);
        ApArticleContent apArticleContent = ApArticleContent.builder().content(apArticleDto.getContent()).build();
        if (apArticleDto.getId() == null) {
            article.setCreatedTime(LocalDateTime.now());
            // 新增
            apArticleMapper.insert(article);
            article.setStaticUrl(generateStaticUrl(article.getId(), apArticleDto.getContent()));
            // 新增的文章有一些默认的配置，更新配置表
            apArticleConfigMapper.insert(ApArticleConfig.builder().articleId(article.getId()).isDown(false).isDelete(false).isComment(true).isForward(true).build());
            // 保存文章内容到文章内容表
            apArticleContent.setArticleId(article.getId());
            apArticleContentMapper.insert(apArticleContent);
        } else {
            // 修改
            if (apArticleMapper.selectById(apArticleDto.getId()) == null) {
                log.warn("更新文章，但文章ID不存在，要更新的文章ID是：{}", apArticleDto.getId());
                // 新增
                article.setCreatedTime(LocalDateTime.now());
                apArticleMapper.insert(article);
            } else {
                log.info("更新文章");
                article.setStaticUrl(generateStaticUrl(article.getId(), apArticleDto.getContent()));
                apArticleMapper.updateById(article);
            }
            // 修改文章内容
            if (apArticleContentMapper.selectOne(new LambdaQueryWrapper<ApArticleContent>().eq(ApArticleContent::getArticleId, article.getId())) == null) {
                log.warn("更新文章内容，但文章内容ID不存在，要更新的文章内容对应的文章ID是：{}", apArticleDto.getId());
                // 保存文章内容到文章内容表
                apArticleContent.setArticleId(article.getId());
                apArticleContentMapper.insert(apArticleContent);
            } else {
                log.info("更新文章内容");
                apArticleContent.setArticleId(article.getId());
                apArticleContentMapper.update(apArticleContent, new LambdaQueryWrapper<ApArticleContent>().eq(ApArticleContent::getArticleId, article.getId()));
            }
        }
        // 保存文章到ES索引
        saveArticleToEsIndex(article, apArticleContent);
        return ResponseResult.okResult(article.getId());
    }

    /**
     * 保存文章到ES索引
     */
    private void saveArticleToEsIndex(ApArticle article, ApArticleContent apArticleContent) {
        log.info("新增或更新文章后，发送kafka消息，同步文章信息到ES索引...");
        SearchArticleVo searchArticleVo = new SearchArticleVo();
        BeanUtils.copyProperties(article, searchArticleVo);
        searchArticleVo.setContent(apArticleContent.getContent());
        kafkaTemplate.send(ArticleMQConstants.ARTICLE_ES_SYNC_TOPIC, JSON.toJSONString(searchArticleVo));
    }


    /**
     * 生成文章的静态url
     */
    protected String generateStaticUrl(Long articleId, String articleContent) {
        if (articleId == null || !StringUtils.hasLength(articleContent)) {
            throw new RuntimeException("生成文章的静态页面失败，文章id或文章内容为空！");
        }
        try {
            // 获取模板，article.ftl是一个freemarker模板文件。我们在配置文件中指定了模板文件的位置：classpath:/templates/
            Template template = configuration.getTemplate("article.ftl");
            // StringWriter是一个可变的字符输出流，可用作字符串缓冲区
            StringWriter stringWriter = new StringWriter();
            // 将json对象转换为map，然后使用freemarker模板引擎生成html文件，文件暂存在stringWriter中
            template.process(Map.of("content", JSON.parseArray(articleContent)), stringWriter);
            // 将html文件上传到文件服务器，返回文件的url
            String htmlUrl = fileStorageService.uploadHtmlFile("", articleId + ".html",
                    new ByteArrayInputStream(stringWriter.toString().getBytes()));
            stringWriter.close();
            // 更新文章的静态url
            // apArticleMapper.updateById(ApArticle.builder().id(apArticle.getId()).staticUrl(htmlUrl).build());
            System.out.printf("htmlUrl: %s%n", htmlUrl);
            return htmlUrl;
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
