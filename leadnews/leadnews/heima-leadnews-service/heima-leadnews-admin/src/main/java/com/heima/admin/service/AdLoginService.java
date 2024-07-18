package com.heima.admin.service;

import com.heima.model.admin.dto.AdLoginDto;
import com.heima.model.admin.vo.AdLoginVo;
import com.heima.model.common.dto.ResponseResult;
import org.springframework.lang.NonNull;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:34
 */
public interface AdLoginService {
    /**
     * 登录
     */
    ResponseResult<AdLoginVo> login(@NonNull AdLoginDto adLoginDto);
}
