package com.heima.wemedia.controller.v1;

import com.heima.model.common.dto.PageResponseResult;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.*;
import com.heima.model.wemedia.entity.WmNews;
import com.heima.model.wemedia.vo.WmNewsAdminQueryVo;
import com.heima.wemedia.service.WmNewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/22 周四 上午10:53
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "自媒体图文内容管理")
@RequestMapping("/api/v1/news")
public class WmNewsController {
    private final WmNewsService wmNewsService;

    /**
     * 分页查询自媒体图文内容列表
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询自媒体图文内容列表")
    public PageResponseResult<List<WmNews>> findList(@RequestBody WmNewsPageReqDto wmNewsPageReqDto) {
        log.info("分页查询自媒体图文内容列表: {}", wmNewsPageReqDto);
        return wmNewsService.findList(wmNewsPageReqDto);
    }

    /**
     * 自媒体图文内容保存或修改
     */
    @PostMapping("/submit")
    @Operation(summary = "自媒体图文内容保存或修改")
    public ResponseResult<?> submit(@RequestBody WmNewsDto wmNewsDto) {
        log.info("自媒体图文内容保存或修改: {}", wmNewsDto);
        return wmNewsService.submit(wmNewsDto);
    }

    @PostMapping("/down_or_up")
    public ResponseResult<?> downOrUp(@RequestBody DownOrUpNewsDto downOrUpNewsDto) {
        log.info("自媒体文章上下架: {}", downOrUpNewsDto);
        return wmNewsService.downOrUp(downOrUpNewsDto);
    }

    @GetMapping("/del_news/{id}")
    @Operation(summary = "删除自媒体文章")
    public ResponseResult<?> deleteNews(@NonNull @PathVariable("id") Long id) {
        log.info("删除自媒体文章: {}", id);
        return wmNewsService.deleteNews(id);
    }

    @PostMapping("/list_vo")
    @Operation(summary = "管理员分页查询文章")
    public PageResponseResult<List<WmNewsAdminQueryVo>> findForAdmin(@RequestBody WmNewsAdminPageQueryDto wmNewsAdminPageQueryDto) {
        log.info("管理员分页查询文章: {}", wmNewsAdminPageQueryDto);
        return wmNewsService.findForAdmin(wmNewsAdminPageQueryDto);
    }

    /**
     * 审核驳回媒体文章
     */
    @PostMapping("/auth_fail")
    @Operation(summary = "审核驳回媒体文章")
    public ResponseResult<?> adminRejectNews(@RequestBody AdminRejectNewsDto adminRejectNewsDto) {
        log.info("审核驳回媒体文章: {}", adminRejectNewsDto);
        return wmNewsService.adminRejectNews(adminRejectNewsDto);
    }

    /**
     * admin根据ID查询自媒体文章
     */
    @GetMapping("/one_vo/{id}")
    @Operation(summary = "admin根据ID查询自媒体文章")
    public ResponseResult<WmNewsAdminQueryVo> findWmNewsForAdminById(@NonNull @PathVariable("id") Long id) {
        log.info("admin根据ID查询自媒体文章: {}", id);
        return wmNewsService.findWmNewsForAdminById(id);
    }

    /**
     * 审核通过媒体文章
     */
    @PostMapping("/auth_pass")
    @Operation(summary = "审核通过媒体文章")
    public ResponseResult<?> adminPassNews(@RequestBody AdminAuthPassNewsDto adminAuthPassNewsDto) {
        log.info("审核通过媒体文章: {}", adminAuthPassNewsDto);
        return wmNewsService.adminPassNews(adminAuthPassNewsDto);
    }
}
