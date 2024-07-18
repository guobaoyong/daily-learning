package com.heima.user.service;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.user.dto.ApUserRealnamePageQueryDto;
import com.heima.model.user.dto.ApUserRealnamePassDto;
import com.heima.model.user.dto.ApUserRealnameRejectDto;
import com.heima.model.user.entity.ApUserRealname;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:32
 */
public interface ApUserRealnameService {
    /**
     * 查询实名认证列表
     */
    ResponseResult<List<ApUserRealname>> list(@NonNull ApUserRealnamePageQueryDto apUserRealnameDto);

    /**
     * 实名认证通过
     */
    ResponseResult<?> pass(@NonNull ApUserRealnamePassDto apUserRealnamePassDto);

    /**
     * 实名认证驳回
     */
    ResponseResult<?> reject(@NonNull ApUserRealnameRejectDto apUserRealnameRejectDto);
}
