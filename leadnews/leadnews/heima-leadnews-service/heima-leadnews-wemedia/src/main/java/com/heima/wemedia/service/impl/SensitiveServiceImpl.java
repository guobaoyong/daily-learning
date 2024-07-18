package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.AddSensitiveDto;
import com.heima.model.wemedia.dto.SensitiveQueryDto;
import com.heima.model.wemedia.dto.UpdateSensitiveDto;
import com.heima.model.wemedia.entity.WmSensitive;
import com.heima.wemedia.mapper.SensitiveMapper;
import com.heima.wemedia.service.SensitiveService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午3:37
 */
@Service
@RequiredArgsConstructor
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, WmSensitive> implements SensitiveService {
    private final SensitiveMapper sensitiveMapper;

    /**
     * 添加敏感词
     */
    @Override
    public ResponseResult<?> add(@NotNull AddSensitiveDto addSensitiveDto) {
        String sensitives = addSensitiveDto.getSensitives();
        if (!StringUtils.hasLength(sensitives)) {
            return ResponseResult.errorResult(400, "敏感词不能为空~");
        }
        if (!sensitiveMapper.selectList(new LambdaQueryWrapper<WmSensitive>().eq(WmSensitive::getSensitives, sensitives)).isEmpty()) {
            return ResponseResult.errorResult(500, "敏感词已存在~");
        }
        WmSensitive wmSensitive = WmSensitive.builder().sensitives(sensitives).createdTime(LocalDateTime.now()).build();
        if (sensitiveMapper.insert(wmSensitive) == 0) {
            return ResponseResult.errorResult(500, "添加失败~");
        }
        return ResponseResult.okResult(wmSensitive);
    }

    /**
     * 删除敏感词
     */
    @Override
    public ResponseResult<?> delete(@NotNull Long id) {
        sensitiveMapper.deleteById(id);
        return ResponseResult.okResult(200, "删除成功~");
    }

    /**
     * 查询敏感词
     */
    @Override
    public ResponseResult<List<WmSensitive>> list(@NotNull SensitiveQueryDto sensitiveQueryDto) {
        if (sensitiveQueryDto.getPage() == null) {
            log.warn("分页参数中没有传递起始页，默认为第1页~");
            sensitiveQueryDto.setPage(1);
        }
        if (sensitiveQueryDto.getSize() == null) {
            return ResponseResult.errorResult(503, "每页显示条数不能为空~");
        }
        String name = sensitiveQueryDto.getName();
        return ResponseResult.okResult(sensitiveMapper.selectPage(new Page<>(sensitiveQueryDto.getPage(), sensitiveQueryDto.getSize()),
                new LambdaQueryWrapper<WmSensitive>().like(StringUtils.hasLength(name), WmSensitive::getSensitives, name)).getRecords());
    }

    /**
     * 更新敏感词
     */
    @Override
    public ResponseResult<?> update(@NotNull UpdateSensitiveDto updateSensitiveDto) {
        if (updateSensitiveDto.getId() == null) {
            return ResponseResult.errorResult(503, "id不能为空~");
        } else if (!StringUtils.hasLength(updateSensitiveDto.getSensitives())) {
            return ResponseResult.errorResult(503, "敏感词不能为空~");
        }
        WmSensitive wmSensitive = sensitiveMapper.selectById(updateSensitiveDto.getId());
        if (wmSensitive == null) {
            return ResponseResult.errorResult(503, "敏感词不存在~");
        }
        wmSensitive.setSensitives(updateSensitiveDto.getSensitives());
        if (sensitiveMapper.updateById(wmSensitive) == 0) {
            return ResponseResult.errorResult(503, "更新失败~");
        }
        return ResponseResult.okResult(200, "更新成功~");
    }
}
