package com.heima.wemedia.service;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.AddSensitiveDto;
import com.heima.model.wemedia.dto.SensitiveQueryDto;
import com.heima.model.wemedia.dto.UpdateSensitiveDto;
import com.heima.model.wemedia.entity.WmSensitive;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午3:36
 */
public interface SensitiveService {
    /**
     * 添加敏感词
     */
    ResponseResult<?> add(@NonNull AddSensitiveDto addSensitiveDto);

    /**
     * 删除敏感词
     */
    ResponseResult<?> delete(@NonNull Long id);

    /**
     * 查询敏感词
     */
    ResponseResult<List<WmSensitive>> list(@NonNull SensitiveQueryDto sensitiveQueryDto);

    /**
     * 更新敏感词
     */
    ResponseResult<?> update(@NonNull UpdateSensitiveDto updateSensitiveDto);
}
