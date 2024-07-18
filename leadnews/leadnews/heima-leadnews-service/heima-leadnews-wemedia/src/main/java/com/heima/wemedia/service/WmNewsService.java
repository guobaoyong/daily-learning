package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dto.PageResponseResult;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.*;
import com.heima.model.wemedia.entity.WmNews;
import com.heima.model.wemedia.vo.WmNewsAdminQueryVo;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 上午10:52
 */
public interface WmNewsService extends IService<WmNews> {
    /**
     * 分页查询自媒体图文内容列表
     */
    PageResponseResult<List<WmNews>> findList(@NonNull WmNewsPageReqDto wmNewsPageReqDto);

    /**
     * 自媒体图文内容保存或修改
     */
    ResponseResult<?> submit(WmNewsDto wmNewsDto);

    /**
     * 自媒体文章上下架
     */
    ResponseResult<?> downOrUp(@NonNull DownOrUpNewsDto downOrUpNewsDto);

    /**
     * 删除自媒体文章
     */
    ResponseResult<?> deleteNews(@NonNull Long id);

    /**
     * 分页查询需要人工审核的文章
     */
    PageResponseResult<List<WmNewsAdminQueryVo>> findForAdmin(@NonNull WmNewsAdminPageQueryDto wmNewsAdminPageQueryDto);

    /**
     * 审核驳回媒体文章
     */
    ResponseResult<?> adminRejectNews(@NonNull AdminRejectNewsDto adminRejectNewsDto);

    /**
     * admin根据ID查询自媒体文章
     */
    ResponseResult<WmNewsAdminQueryVo> findWmNewsForAdminById(@NonNull Long id);

    /**
     * admin审核通过自媒体文章
     */
    ResponseResult<?> adminPassNews(@NonNull AdminAuthPassNewsDto adminAuthPassNewsDto);
}
