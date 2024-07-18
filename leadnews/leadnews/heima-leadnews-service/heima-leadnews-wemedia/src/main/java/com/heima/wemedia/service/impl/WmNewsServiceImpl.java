package com.heima.wemedia.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.api.article.IArticleClient;
import com.heima.common.constants.WemediaConstants;
import com.heima.common.constants.WmNewsMQConstants;
import com.heima.common.exception.CustomException;
import com.heima.model.article.dto.ApArticleDto;
import com.heima.model.common.dto.PageResponseResult;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.common.enums.Layout;
import com.heima.model.wemedia.dto.*;
import com.heima.model.wemedia.entity.WmMaterial;
import com.heima.model.wemedia.entity.WmNews;
import com.heima.model.wemedia.entity.WmNewsMaterial;
import com.heima.model.wemedia.entity.WmUser;
import com.heima.model.wemedia.enums.NewsEnable;
import com.heima.model.wemedia.enums.NewsMaterialRelationType;
import com.heima.model.wemedia.enums.NewsStatus;
import com.heima.model.wemedia.vo.WmNewsAdminQueryVo;
import com.heima.wemedia.mapper.*;
import com.heima.wemedia.service.WmNewsService;
import com.heima.wemedia.service.WmNewsTaskService;
import com.heima.wemedia.thread.WmUserThreadLocalUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 上午10:52
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    private final WmNewsMapper wmNewsMapper;
    private final WmNewsMaterialMapper wmNewsMaterialMapper;
    private final WmMaterialMapper wmMaterialMapper;
    private final WmNewsTaskService wmNewsTaskService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final WmUserMapper wmUserMapper;
    private final IArticleClient articleClient;
    private final WmChannelMapper wmChannelMapper;

    /**
     * 分页查询自媒体图文内容列表
     */
    @Override
    public PageResponseResult<List<WmNews>> findList(@NotNull WmNewsPageReqDto wmNewsPageReqDto) {
        Integer currentPage = wmNewsPageReqDto.getPage();
        Integer pageSize = wmNewsPageReqDto.getSize();
        Page<WmNews> wmNewsPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 当前状态
        Short status = wmNewsPageReqDto.getStatus();
        if (status != null) {
            lambdaQueryWrapper.eq(WmNews::getStatus, status);
        }
        // 开始时间（发布时间）
        Date beginPubDate = wmNewsPageReqDto.getBeginPubDate();
        if (beginPubDate != null) {
            lambdaQueryWrapper.ge(WmNews::getPublishTime, beginPubDate);
        }
        // 结束时间（发布时间）
        Date endPubDate = wmNewsPageReqDto.getEndPubDate();
        if (endPubDate != null) {
            lambdaQueryWrapper.le(WmNews::getPublishTime, endPubDate);
        }
        // 频道ID
        Integer channelId = wmNewsPageReqDto.getChannelId();
        if (channelId != null) {
            lambdaQueryWrapper.eq(WmNews::getChannelId, channelId);
        }
        // 关键字查询
        String keyword = wmNewsPageReqDto.getKeyword();
        if (StringUtils.hasLength(keyword)) {
            lambdaQueryWrapper.like(WmNews::getTitle, keyword);
        }
        // 按发布时间降序排序
        lambdaQueryWrapper.orderByDesc(WmNews::getPublishTime);
        wmNewsMapper.selectPage(wmNewsPage, lambdaQueryWrapper);
        return new PageResponseResult<List<WmNews>>(
                currentPage,
                pageSize,
                (int) wmNewsPage.getTotal())
                .ok(200, wmNewsPage.getRecords(), "操作成功");
    }

    /**
     * 自媒体图文内容保存或修改
     */
    @Override
    @GlobalTransactional
    public ResponseResult<?> submit(WmNewsDto wmNewsDto) {
        // 参数无效
        if (wmNewsDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 封面图片
        List<String> coverImages = wmNewsDto.getImages().stream().filter(StringUtils::hasLength).toList();
        // 图文内容数据。顾名思义，它分为文字类型和图片类型
        List<Map<String, String>> contentList = JSONObject.parseObject(wmNewsDto.getContent(), new TypeReference<List<Map<String, String>>>() {
        }.getType());
        // 筛选出图片类型的信息
        List<String> contentImageList = contentList.stream().filter(stringStringMap -> "image".equals(stringStringMap.get("type"))).map(stringStringMap -> stringStringMap.get("value")).toList();
        // 文章状态，比如草稿、待审核等
        Short status = wmNewsDto.getStatus();
        Date now = new Date();
        WmNews wmNews = WmNews.builder().images(String.join(",", coverImages)).content(wmNewsDto.getContent()).userId(WmUserThreadLocalUtil.getWmUser().getId()).submittedTime(status.equals(NewsStatus.SUBMIT.getValue()) ? now : null).enable(NewsEnable.ENABLE).publishTime(wmNewsDto.getPublishTime()).status(NewsStatus.valueOf(wmNewsDto.getStatus())).labels(wmNewsDto.getLabels()).channelId(wmNewsDto.getChannelId()).title(wmNewsDto.getTitle()).build();
        // 对于新增的文章，设置创建时间
        if (wmNewsDto.getId() == null) {
            wmNews.setCreatedTime(now);
        }
        // 我们的数据库不接受负数的图文类型（WmNews.Type）
        if (!WemediaConstants.NEWS_TYPE_AUTO_SET_IMAGE.equals(wmNewsDto.getType())) {
            wmNews.setType(Layout.valueOf(wmNewsDto.getType()));
        }
        // 如果是草稿，直接结束
        if (status.equals(NewsStatus.DRAFT.getValue())) {
            log.info("保存草稿");
            saveOrUpdate(wmNews);
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        wmNews.setStatus(NewsStatus.SUBMIT);
        // 保存内容图片与文章的关联关系
        saveNewsMaterialRelation(wmNews, contentImageList, NewsMaterialRelationType.CONTENT_REFERENCE);
        // 保存封面图片与文章的关联关系
        if (Objects.equals(wmNewsDto.getType(), WemediaConstants.NEWS_TYPE_AUTO_SET_IMAGE)) {
            if (contentImageList.size() >= 3) {
                wmNews.setType(Layout.MULTIPLE_IMG);
                wmNews.setImages(String.join(",", contentImageList.subList(0, 3)));
            } else if (!contentImageList.isEmpty()) {
                wmNews.setType(Layout.SINGLE_IMG);
                wmNews.setImages(contentImageList.get(0));
            } else {
                wmNews.setType(Layout.NO_IMG);
            }
        }
        saveOrUpdate(wmNews);
        if (!coverImages.isEmpty()) {
            saveNewsMaterialRelation(wmNews, coverImages, NewsMaterialRelationType.COVER);
        }

        wmNewsTaskService.addNewsToTaskQueue(wmNews.getId(), wmNews.getPublishTime());

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 保存文章与素材的关联关系
     *
     * @param wmNews       文章
     * @param materials    素材URL列表
     * @param materialType 素材类型，0表示内容中的图片，1表示封面图片
     */
    private void saveNewsMaterialRelation(WmNews wmNews, List<String> materials, NewsMaterialRelationType materialType) {
        // 删除旧的文章与素材的关联关系
        wmNewsMaterialMapper.delete(new LambdaQueryWrapper<WmNewsMaterial>().eq(WmNewsMaterial::getNewsId, wmNews.getId()).eq(WmNewsMaterial::getType, materialType));
        if (materials.isEmpty()) {
            return;
        }
        ArrayList<WmNewsMaterial> wmNewsMaterials = new ArrayList<>();
        // 根据url查询素材
        List<WmMaterial> wmMaterials = wmMaterialMapper.selectList(new LambdaQueryWrapper<WmMaterial>().in(WmMaterial::getUrl, materials));
        // 不同的WmMaterial可能是同一个URL的素材，当然这是不提倡的，但是我们还是要处理一下
        HashMap<String, ArrayList<WmMaterial>> wmUrlMaterialMap = new HashMap<>();
        // 将素材按URL分类
        wmMaterials.forEach(wmMaterial -> {
            if (wmUrlMaterialMap.containsKey(wmMaterial.getUrl())) {
                wmUrlMaterialMap.get(wmMaterial.getUrl()).add(wmMaterial);
            }
            wmUrlMaterialMap.put(wmMaterial.getUrl(), new ArrayList<>(List.of(wmMaterial)));
        });
        // 理论上，我们接收到的素材URL都是数据库中存在的，所以wmUrlMaterialMap得键值对数量应该等于materials的数量
        if (wmUrlMaterialMap.keySet().size() != materials.size()) {
            throw new CustomException(AppHttpCodeEnum.MATERIAL_REFERENCE_INVALID);
        }
        wmUrlMaterialMap.keySet().forEach(url -> wmNewsMaterials.add(WmNewsMaterial.builder().newsId(wmNews.getId()).materialId(wmUrlMaterialMap.get(url).get(0).getId()).type(materialType).build()));
        // 批量插入素材与文章的关联关系
        wmNewsMaterialMapper.insertBatch(wmNewsMaterials);
    }

    /**
     * 自媒体文章上下架
     */
    @Override
    @GlobalTransactional
    public ResponseResult<?> downOrUp(@NotNull DownOrUpNewsDto downOrUpNewsDto) {
        if (downOrUpNewsDto.getId() == null || downOrUpNewsDto.getEnable() == null || !NewsEnable.isValid(downOrUpNewsDto.getEnable())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = wmNewsMapper.selectById(downOrUpNewsDto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else if (!wmNews.getStatus().equals(NewsStatus.PUBLISHED)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "只有已发布的文章才能上下架");
        }
        wmNews.setEnable(NewsEnable.of(downOrUpNewsDto.getEnable()));
        wmNewsMapper.updateById(wmNews);
        // 发送消息到kafka，通知文章微服务更新文章状态
        if (wmNews.getArticleId() != null) {
            kafkaTemplate.send(WmNewsMQConstants.WM_NEWS_UP_OR_DOWN_TOPIC, JSONObject.toJSONString(Map.of("articleId", wmNews.getArticleId(), "enable", downOrUpNewsDto.getEnable())));
        }
        return ResponseResult.okResult(200, "下架文章成功！");
    }

    /**
     * 删除自媒体文章
     */
    @Override
    @GlobalTransactional
    public ResponseResult<?> deleteNews(@NonNull Long id) {
        WmNews wmNews = wmNewsMapper.selectById(id);
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else if (wmNews.getStatus().equals(NewsStatus.PUBLISHED) && wmNews.getEnable().equals(NewsEnable.ENABLE)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "已上架的文章不能删除！");
        }
        wmNewsMapper.deleteById(id);
        if (wmNews.getArticleId() != null) {
            kafkaTemplate.send(WmNewsMQConstants.WM_NEWS_DELETE_TOPIC, JSONObject.toJSONString(Map.of("articleId", wmNews.getArticleId())));
        }
        return ResponseResult.okResult(200, "删除文章成功！");
    }

    /**
     * 分页查询需要人工审核的文章
     */
    @Override
    public PageResponseResult<List<WmNewsAdminQueryVo>> findForAdmin(@NotNull WmNewsAdminPageQueryDto wmNewsAdminPageQueryDto) {
        Integer page = wmNewsAdminPageQueryDto.getPage();
        Integer size = wmNewsAdminPageQueryDto.getSize();
        if (page == null || page < 0 || size == null || size < 0) {
            return PageResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        String title = wmNewsAdminPageQueryDto.getTitle();
        Short status = wmNewsAdminPageQueryDto.getStatus();
        // 查询文章
        List<WmNews> records = wmNewsMapper
                .selectPage(new Page<>(page, size), new LambdaQueryWrapper<WmNews>()
                        .like(title != null, WmNews::getTitle, title)
                        .eq(status != null, WmNews::getStatus, NewsStatus.valueOf(status))
                        .orderByDesc(WmNews::getCreatedTime))
                .getRecords();
        // 存储作者ID
        HashSet<Long> userIds = new HashSet<>();
        records.forEach(wmNews -> userIds.add(wmNews.getUserId()));
        HashMap<Long, WmUser> wmUserHashMap = new HashMap<>();
        // 查询文章作者
        wmUserMapper.selectList(new LambdaQueryWrapper<WmUser>().in(!userIds.isEmpty(), WmUser::getId, userIds)).forEach(wmUser -> wmUserHashMap.put(wmUser.getId(), wmUser));
        return new PageResponseResult<List<WmNewsAdminQueryVo>>(page, size, records.size()).ok(200, records.stream()
                .map(wmNews -> {
                    WmNewsAdminQueryVo wmNewsAdminQueryVo = new WmNewsAdminQueryVo();
                    BeanUtils.copyProperties(wmNews, wmNewsAdminQueryVo);
                    // 设置作者名称
                    wmNewsAdminQueryVo.setAuthorName(wmUserHashMap.get(wmNews.getUserId()).getName());
                    return wmNewsAdminQueryVo;
                })
                .toList(), "查询成功，管理员！");
    }

    /**
     * 审核驳回媒体文章
     */
    @Override
    public ResponseResult<?> adminRejectNews(@NonNull AdminRejectNewsDto adminRejectNewsDto) {
        if (adminRejectNewsDto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = wmNewsMapper.selectById(adminRejectNewsDto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else if (!(wmNews.getStatus().equals(NewsStatus.MANUAL_REVIEW) || wmNews.getStatus().equals(NewsStatus.SUBMIT))) {
            return ResponseResult.errorResult(503, "只有待审核的文章才能驳回！");
        }
        wmNews.setStatus(NewsStatus.FAILED);
        wmNews.setReason(adminRejectNewsDto.getMsg());
        int updatedById = wmNewsMapper.updateById(wmNews);
        if (updatedById == 0) {
            return ResponseResult.errorResult(503, "驳回失败！");
        }
        return ResponseResult.okResult(200, "驳回成功！");
    }

    /**
     * admin根据ID查询自媒体文章
     */
    @Override
    public ResponseResult<WmNewsAdminQueryVo> findWmNewsForAdminById(@NotNull Long id) {
        WmNews wmNews = wmNewsMapper.selectById(id);
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        WmNewsAdminQueryVo wmNewsAdminQueryVo = new WmNewsAdminQueryVo();
        BeanUtils.copyProperties(wmNews, wmNewsAdminQueryVo);
        wmNewsAdminQueryVo.setAuthorName(wmUserMapper.selectById(wmNews.getUserId()).getName());
        return ResponseResult.okResult(wmNewsAdminQueryVo);
    }

    /**
     * admin审核通过自媒体文章
     */
    @Override
    public ResponseResult<?> adminPassNews(@NonNull AdminAuthPassNewsDto adminAuthPassNewsDto) {
        if (adminAuthPassNewsDto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = wmNewsMapper.selectById(adminAuthPassNewsDto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else if (!(wmNews.getStatus().equals(NewsStatus.MANUAL_REVIEW) || wmNews.getStatus().equals(NewsStatus.SUBMIT))) {
            return ResponseResult.errorResult(503, "只有待审核的文章才能审核通过：)");
        } else if (wmNews.getArticleId() != null) {
            return ResponseResult.errorResult(503, "该文章已经发布！");
        }
        wmNews.setStatus(NewsStatus.PUBLISHED);
        wmNewsMapper.updateById(wmNews);
        // 远程调用文章微服务，保存文章
        ResponseResult<Long> saveArticleRes = articleClient.saveArticle(ApArticleDto.apArticleDtoBuilder()
                .id(wmNews.getArticleId())
                .title(wmNews.getTitle())
                .authorId(wmNews.getUserId())
                .authorName(wmUserMapper.selectById(wmNews.getUserId()).getName())
                .channelId(wmNews.getChannelId())
                .channelName(wmChannelMapper.selectById(wmNews.getChannelId()).getName())
                .layout(wmNews.getType())
                .content(wmNews.getContent())
                .images(wmNews.getImages())
                .labels(wmNews.getLabels())
                .build());
        if (saveArticleRes.getCode() != 200) {
            log.error("远程调用文章微服务保存文章失败：{}", saveArticleRes);
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
        } else {
            log.info("远程调用文章微服务保存文章成功：{}", saveArticleRes);
            return ResponseResult.okResult(200, "审核通过成功！");
        }
    }
}
