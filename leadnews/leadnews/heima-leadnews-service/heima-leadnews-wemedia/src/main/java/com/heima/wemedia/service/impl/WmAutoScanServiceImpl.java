package com.heima.wemedia.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.heima.api.article.IArticleClient;
import com.heima.common.aliyun.green.v2.GreenImageScan;
import com.heima.common.aliyun.green.v2.GreenTextScan;
import com.heima.common.tess4j.Tess4jClient;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.dto.ApArticleDto;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.entity.WmChannel;
import com.heima.model.wemedia.entity.WmNews;
import com.heima.model.wemedia.entity.WmSensitive;
import com.heima.model.wemedia.entity.WmUser;
import com.heima.model.wemedia.enums.NewsStatus;
import com.heima.utils.common.SensitiveWordUtil;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmSensitiveMapper;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmAutoScanService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 高翔宇
 * @since 2024/3/6 周三 12:41
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@GlobalTransactional
public class WmAutoScanServiceImpl implements WmAutoScanService {
    private final WmNewsMapper wmNewsMapper;
    private final GreenTextScan greenTextScan;
    private final GreenImageScan greenImageScan;
    private final IArticleClient articleClient;
    private final WmChannelMapper wmChannelMapper;
    private final WmUserMapper wmUserMapper;
    private final FileStorageService fileStorageService;
    private final WmSensitiveMapper wmSensitiveMapper;
    private final Tess4jClient tess4jClient;

    /**
     * 审核文章
     * <p>
     * {@link Async} 注解表示该方法是一个异步方法，它会在一个新的线程中执行，而不会阻塞当前线程
     * </p>
     *
     * @param wmNewsId 文章id
     */
    @Override
    // @Async
    public void reviewNesById(Long wmNewsId) {
        log.info("审核文章：{}", wmNewsId);
        WmNews wmNews = wmNewsMapper.selectById(wmNewsId);
        if (wmNews == null) {
            throw new RuntimeException("文章不存在");
        }
        List<String> images = new ArrayList<>(Arrays.stream(wmNews.getImages().split(",")).toList());
        StringBuilder content = new StringBuilder();
        ArrayList<Map<String, String>> contentMaps = JSON.parseObject(wmNews.getContent(), new TypeReference<>() {
        });
        if (contentMaps != null && !contentMaps.isEmpty()) {
            contentMaps.forEach(contentMap -> {
                if ("text".equals(contentMap.get("type"))) {
                    content.append(contentMap.get("value"));
                } else if ("image".equals(contentMap.get("type"))) {
                    images.add(contentMap.get("value"));
                }
            });
        }
        // 检查文本中是否存在自定义的敏感词
        if (customSentinelTextScanHandler(wmNews, content.toString())) {
            log.warn("文本审核未通过，检查到自定义的敏感词");
            wmNewsMapper.updateById(wmNews);
            return;
        }
        // 全面审核文本和图片
        if (wmNews.getStatus().equals(NewsStatus.SUBMIT)) {
            if (!checkTextHandler(wmNews, content.toString())) {
                log.warn("文本审核未通过");
                wmNewsMapper.updateById(wmNews);
                return;
            } else {
                log.info("文本审核通过");
                if (!checkImagesHandler(wmNews, images)) {
                    log.warn("图片审核未通过");
                    wmNewsMapper.updateById(wmNews);
                    return;
                } else {
                    log.info("图片审核通过");
                    wmNews.setStatus(NewsStatus.PASSED);
                    wmNewsMapper.updateById(wmNews);
                }
            }
        } else {
            log.error("文章状态不是待审核的状态，无法审核");
            return;
        }
        // 保存文章
        ResponseResult<Long> saveArticleResult = savedArticle(wmNews);
        if (saveArticleResult == null || saveArticleResult.getCode() != 200) {
            log.error("文章审核成功，但发布失败。res：{}", saveArticleResult);
            wmNews.setStatus(NewsStatus.PASSED);
        } else {
            log.info("文章保存成功，更新自媒体文章状态为已发布，并将文章id更新到自媒体文章表中");
            wmNews.setArticleId(saveArticleResult.getData());
            wmNews.setStatus(NewsStatus.PUBLISHED);
            wmNews.setReason("");
        }
        wmNewsMapper.updateById(wmNews);
    }

    /**
     * 自定义文本扫描处理器，扫描文本中是否存在自定义的敏感词
     *
     * @param wmNews  {@link WmNews}
     * @param content 文本
     * @return true-存在自定义的敏感词，false-不存在存在自定义的敏感词
     */
    private boolean customSentinelTextScanHandler(WmNews wmNews, String content) {
        List<String> sensitivesList = wmSensitiveMapper.selectList(null).stream().map(WmSensitive::getSensitives).toList();
        SensitiveWordUtil.initMap(sensitivesList);
        Map<String, Integer> result = SensitiveWordUtil.matchWords(content);
        if (!result.isEmpty()) {
            log.warn("文本中存在敏感词：{}", result);
            wmNews.setReason("文本中存在敏感词：" + StringUtils.collectionToCommaDelimitedString(result.keySet()));
            wmNews.setStatus(NewsStatus.FAILED);
            return true;
        }
        return false;
    }

    /**
     * 保存文章，它应该在文章审核无误后执行
     *
     * @param wmNews {@link WmNews}
     * @return 远程调用的响应结果，携带保存的文章的id
     */
    private ResponseResult<Long> savedArticle(WmNews wmNews) {
        WmUser wmUser = wmUserMapper.selectById(wmNews.getUserId());
        WmChannel wmChannel = wmChannelMapper.selectById(wmNews.getChannelId());
        LocalDateTime now = LocalDateTime.now();
        ApArticleDto apArticleDto = new ApArticleDto();
        apArticleDto.setTitle(wmNews.getTitle());
        apArticleDto.setContent(wmNews.getContent());
        apArticleDto.setAuthorId(wmNews.getUserId());
        apArticleDto.setAuthorName(wmUser != null ? wmUser.getName() : null);
        apArticleDto.setImages(wmNews.getImages());
        apArticleDto.setChannelId(wmNews.getChannelId());
        apArticleDto.setChannelName(wmChannel != null ? wmChannel.getName() : null);
        apArticleDto.setLabels(wmNews.getLabels());
        apArticleDto.setLayout(wmNews.getType());
        apArticleDto.setPublishTime(now);
        Long articleId = wmNews.getArticleId();
        if (articleId == null) {
            apArticleDto.setCreatedTime(now);
        } else {
            apArticleDto.setId(articleId);
        }
        return articleClient.saveArticle(apArticleDto);
    }


    /**
     * 审核文本
     *
     * @param wmNews  {@link WmNews}，审核过程中会根据审核结果
     * @param content 文章内容，纯文本，而非json
     * @return true-审核通过 false-审核不通过
     */
    private boolean checkTextHandler(WmNews wmNews, String content) {
        if (StringUtils.hasLength(wmNews.getTitle() + content)) {
            // 审核文本
            Map<String, String> scanTextRes = greenTextScan.checkText(wmNews.getTitle() + content, "chat_detection");
            if (scanTextRes == null) {
                log.error("调用阿里云文本审核接口响应为null，需要人工审核");
                wmNews.setReason("文本审核失败，需要人工审核");
                wmNews.setStatus(NewsStatus.MANUAL_REVIEW);
                return false;
            }
            int code = Integer.parseInt(scanTextRes.get("code"));
            String labels = scanTextRes.get("labels");
            String[] labelArr = Arrays.stream(labels.split(",")).filter(StringUtils::hasLength).toArray(String[]::new);
            String reason = scanTextRes.get("reason");
            // List<String> allLabels = List.of("ad", "political_content", "profanity", "contraband", "sexual_content", "violence", "nonsense", "negative_content", "religion", "cyberbullying", "ad_compliance", "C_customized");
            if (code != 200) {
                log.error("自动审核文章的过程中出现异常，code：{}，message：{}", code, scanTextRes.get("message"));
                wmNews.setReason("自动审核文章的过程中出现异常");
                wmNews.setStatus(NewsStatus.MANUAL_REVIEW);
                return false;
            } else if (labelArr.length > 1) {
                log.error("文本审核不通过，labelArr：{}，reason：{}", labelArr, reason);
                wmNews.setReason("文本审核不通过");
                wmNews.setStatus(NewsStatus.FAILED);
                return false;
            } else if (labelArr.length > 0) {
                log.warn("文本涉嫌违规，需要人工审核");
                wmNews.setReason("文本涉嫌违规，需要人工审核");
                wmNews.setStatus(NewsStatus.MANUAL_REVIEW);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 审核图片
     *
     * @param wmNews {@link WmNews}
     * @param images 图片url集合
     * @return true-审核通过，false-审核不通过
     */
    private boolean checkImagesHandler(WmNews wmNews, List<String> images) {
        images = images.stream().filter(StringUtils::hasLength).toList();
        if (images.isEmpty()) {
            return true;
        }
        List<byte[]> imageByteArrays = images.stream().distinct().map(fileStorageService::downLoadFile).toList();
        for (byte[] bytes : imageByteArrays) {
            try {
                String ocrResult = tess4jClient.doOcr(ImageIO.read(new ByteArrayInputStream(bytes)));
                log.info("图片文字识别结果：{}", ocrResult);
                if (customSentinelTextScanHandler(wmNews, ocrResult)) {
                    log.warn("在图片中检查到自定义的敏感词");
                    return false;
                }
            } catch (Exception e) {
                log.error("图片识别失败，异常：{}", e.getMessage());
            }
        }
        // 审核图片
        for (int i = 0; i < imageByteArrays.size(); i++) {
            byte[] bytes = imageByteArrays.get(i);
            Map<String, String> scanImageRes = greenImageScan.checkImageByBytes(bytes, "baselineCheck");
            log.info("图片审核结果{}：{}", i, scanImageRes);
            if (scanImageRes == null) {
                log.error("调用阿里云图片审核接口响应为null，需要人工审核");
                wmNews.setReason("图片审核失败，需要人工审核");
                wmNews.setStatus(NewsStatus.MANUAL_REVIEW);
                return false;
            } else if (!"200".equals(scanImageRes.get("code"))) {
                log.error("自动审核图片的过程中出现异常，code：{}，message：{}", scanImageRes.get("code"), scanImageRes.get("message"));
                wmNews.setReason("自动审核图片的过程中出现异常");
                wmNews.setStatus(NewsStatus.MANUAL_REVIEW);
                return false;
            } else {
                HashMap<String, String> resMap = JSON.parseObject(scanImageRes.get("results"), new TypeReference<>() {
                });
                if (!resMap.isEmpty() && (resMap.containsKey("label") && !"nonLabel".equals(resMap.get("label")))) {
                    for (String value : resMap.values()) {
                        float floatValue = Float.parseFloat(value);
                        if (floatValue > 0.8) {
                            log.error("图片涉嫌违规，审核不通过");
                            wmNews.setReason("图片涉嫌违规，审核不通过");
                            wmNews.setStatus(NewsStatus.FAILED);
                            return false;
                        } else if (floatValue > 0.5) {
                            log.warn("图片涉嫌违规，需要人工审核");
                            wmNews.setReason("图片涉嫌违规，需要人工审核");
                            wmNews.setStatus(NewsStatus.MANUAL_REVIEW);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
