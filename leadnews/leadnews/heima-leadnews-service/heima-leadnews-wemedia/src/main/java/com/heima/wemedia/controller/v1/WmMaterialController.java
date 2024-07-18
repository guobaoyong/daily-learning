package com.heima.wemedia.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.WmMaterialPageReqDto;
import com.heima.model.wemedia.entity.WmMaterial;
import com.heima.model.wemedia.vo.UploadPictureVo;
import com.heima.wemedia.service.WmMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 素材管理接口
 *
 * @author 高翔宇
 * @since 2024/2/17 周六 下午7:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/material")
@Slf4j
@Tag(name = "素材管理接口")
public class WmMaterialController {
    private final WmMaterialService wmMaterialService;

    /**
     * 上传素材图片
     */
    @PostMapping(value = "/upload_picture", headers = "content-type=multipart/form-data")
    @Operation(summary = "上传素材图片")
    public ResponseResult<UploadPictureVo> upload(@RequestParam("multipartFile") MultipartFile multipartFile) {
        log.info("上传图片...");
        return wmMaterialService.uploadPicture(multipartFile);
    }

    /**
     * 分页查询素材列表
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询素材列表")
    ResponseResult<List<WmMaterial>> listMaterials(@RequestBody WmMaterialPageReqDto wmMaterialPageReqDto) {
        log.info("分页查询素材列表：{}", wmMaterialPageReqDto);
        return wmMaterialService.listMaterial(wmMaterialPageReqDto);
    }

    /**
     * 删除图片
     */
    @GetMapping("/del_picture/{id}")
    @Operation(summary = "删除图片")
    public ResponseResult<?> delPicture(@PathVariable Long id) {
        log.info("删除图片：{}", id);
        return wmMaterialService.delPicture(id);
    }
    /**
     * 收藏图片
     */
    @GetMapping("/collect/{id}")
    @Operation(summary = "收藏图片")
    public ResponseResult<?> collect(@PathVariable Long id) {
        log.info("收藏图片：{}", id);
        return wmMaterialService.collectPicture(id);
    }

    /**
     * 取消收藏图片
     */
    @GetMapping("/cancel_collect/{id}")
    @Operation(summary = "取消收藏图片")
    public ResponseResult<?> cancelCollect(@PathVariable Long id) {
        log.info("取消收藏图片：{}", id);
        return wmMaterialService.cancelCollectPicture(id);
    }
}
