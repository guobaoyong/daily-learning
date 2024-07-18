package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dto.PageResponseResult;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.entity.WmMaterial;
import com.heima.model.wemedia.dto.WmMaterialPageReqDto;
import com.heima.model.wemedia.vo.UploadPictureVo;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/17 周六 下午7:59
 */
public interface WmMaterialService extends IService<WmMaterial> {
    /**
     * 上传图片
     */
    ResponseResult<UploadPictureVo> uploadPicture(@NonNull MultipartFile multipartFile);

    /**
     * 查询素材列表
     */
    PageResponseResult<List<WmMaterial>> listMaterial(@NonNull WmMaterialPageReqDto wmMaterialPageReqDto);

    /**
     * 删除图片
     */
    ResponseResult<?> delPicture(@NonNull Long id);

    /**
     * 收藏图片
     */
    ResponseResult<?> collectPicture(@NonNull Long id);

    /**
     * 取消收藏图片
     */
    ResponseResult<?> cancelCollectPicture(@NonNull Long id);
}
