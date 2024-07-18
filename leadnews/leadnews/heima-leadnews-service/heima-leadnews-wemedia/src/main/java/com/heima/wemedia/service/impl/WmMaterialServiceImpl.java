package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.impl.MinIOFileStorageService;
import com.heima.model.common.dto.PageResponseResult;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dto.WmMaterialPageReqDto;
import com.heima.model.wemedia.entity.WmMaterial;
import com.heima.model.wemedia.enums.MaterialIsCollection;
import com.heima.model.wemedia.enums.MaterialType;
import com.heima.model.wemedia.vo.UploadPictureVo;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import com.heima.wemedia.thread.WmUserThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 高翔宇
 * @since 2024/2/17 周六 下午7:59
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {
    private final MinIOFileStorageService minIOFileStorageService;
    private final WmMaterialMapper wmMaterialMapper;

    /**
     * 上传图片
     */
    @Override
    public ResponseResult<UploadPictureVo> uploadPicture(@NotNull MultipartFile multipartFile) {
        String imgUrl;
        if (multipartFile.getSize() == 0) {
            // 参数为空或文件大小为0
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        } else {
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null) {
                // 文件名为空
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
            }
            try {
                imgUrl = minIOFileStorageService.uploadImgFile(
                        "",
                        String.valueOf(UUID.randomUUID()).replace("-", "") +
                                originalFilename.substring(originalFilename.lastIndexOf(".")),
                        multipartFile.getInputStream());
                // 保存图片信息到数据库
                WmMaterial wmMaterial = WmMaterial.builder()
                        .userId(WmUserThreadLocalUtil.getWmUser().getId())
                        .url(imgUrl)
                        .type(MaterialType.IMAGE)
                        .isCollection(MaterialIsCollection.NO)
                        .createdTime(new Date()).build();
                wmMaterialMapper.insert(wmMaterial);
                log.info("上传图片到 MinIO 成功，url: {}", imgUrl);
                return ResponseResult.okResult(
                        UploadPictureVo.builder()
                                .id(wmMaterial.getId())
                                .userId(wmMaterial.getUserId())
                                .url(imgUrl)
                                .type(wmMaterial.getType().getValue())
                                .isCollection(wmMaterial.getIsCollection().getValue())
                                .createdTime(wmMaterial.getCreatedTime()).build()
                );
            } catch (IOException e) {
                log.error("上传图片失败", e);
            }
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 查询素材列表
     */
    @Override
    public PageResponseResult<List<WmMaterial>> listMaterial(@NotNull WmMaterialPageReqDto wmMaterialPageReqDto) {
        // 校验参数
        wmMaterialPageReqDto.checkParam();
        Integer current = wmMaterialPageReqDto.getPage();
        Integer size = wmMaterialPageReqDto.getSize();
        // 分页查询
        Page<WmMaterial> wmMaterialPage = new Page<>(current, size);
        short isCollection = wmMaterialPageReqDto.getIsCollection();
        wmMaterialMapper.selectPage(wmMaterialPage, new LambdaQueryWrapper<WmMaterial>()
                .eq(Objects.equals(MaterialIsCollection.fromValue(isCollection), MaterialIsCollection.YES), WmMaterial::getIsCollection, MaterialIsCollection.YES));
        List<WmMaterial> wmMaterials = wmMaterialPage.getRecords();
        return new PageResponseResult<List<WmMaterial>>(current, size, wmMaterials.size()).ok(wmMaterials);
    }

    /**
     * 删除图片
     */
    @Override
    public ResponseResult<?> delPicture(@NotNull Long id) {
        WmMaterial wmMaterial = wmMaterialMapper.selectById(id);
        if (wmMaterial == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        int deleteById = wmMaterialMapper.deleteById(id);
        if (deleteById == 0) {
            return ResponseResult.errorResult(503, "删除失败");
        }
        return ResponseResult.okResult(200, "删除成功");
    }

    /**
     * 收藏图片
     */
    @Override
    public ResponseResult<?> collectPicture(@NotNull Long id) {
        WmMaterial wmMaterial = wmMaterialMapper.selectById(id);
        if (wmMaterial == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        wmMaterial.setIsCollection(MaterialIsCollection.YES);
        int updateById = wmMaterialMapper.updateById(wmMaterial);
        if (updateById == 0) {
            return ResponseResult.errorResult(503, "收藏失败");
        }
        return ResponseResult.okResult(200, "收藏成功");
    }

    /**
     * 取消收藏图片
     */
    @Override
    public ResponseResult<?> cancelCollectPicture(@NotNull Long id) {
        WmMaterial wmMaterial = wmMaterialMapper.selectById(id);
        if (wmMaterial == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        wmMaterial.setIsCollection(MaterialIsCollection.NO);
        int updateById = wmMaterialMapper.updateById(wmMaterial);
        if (updateById == 0) {
            return ResponseResult.errorResult(503, "取消收藏失败");
        }
        return ResponseResult.okResult(200, "取消收藏成功");
    }
}
