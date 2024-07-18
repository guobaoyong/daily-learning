package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dto.ChannelPageQueryDto;
import com.heima.model.wemedia.dto.SaveChannelDto;
import com.heima.model.wemedia.dto.UpdateChannelDto;
import com.heima.model.wemedia.entity.WmChannel;
import com.heima.wemedia.mapper.WmChannelMapper;
import com.heima.wemedia.service.WmChannelService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024-02-20, 周二, 11:42
 */
@Service
@RequiredArgsConstructor
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel>
        implements WmChannelService {
    private final WmChannelMapper wmChannelMapper;

    /**
     * 查询所有可用频道
     */
    @Override
    public ResponseResult<List<WmChannel>> listChannels() {
        return ResponseResult.okResult(wmChannelMapper.selectList(new LambdaQueryWrapper<WmChannel>()
                .eq(WmChannel::getStatus, true)
                .orderByAsc(WmChannel::getOrd)
                .orderByDesc(WmChannel::getCreatedTime)));
    }

    /**
     * 分页查询频道
     */
    @Override
    public ResponseResult<List<WmChannel>> pageQuery(@NotNull ChannelPageQueryDto channelPageQueryDto) {
        String name = channelPageQueryDto.getName();
        Integer page = channelPageQueryDto.getPage();
        Integer size = channelPageQueryDto.getSize();
        if (page == null || page < 1 || size == null || size < 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return new ResponseResult<List<WmChannel>>().ok(200, wmChannelMapper
                .selectPage(
                        new Page<>(page, size),
                        new LambdaQueryWrapper<WmChannel>()
                                .like(StringUtils.hasLength(name), WmChannel::getName, name)
                                .orderByAsc(WmChannel::getOrd)
                                .orderByDesc(WmChannel::getCreatedTime))
                .getRecords(), "查询成功");
    }

    /**
     * 新增频道
     */
    @Override
    public ResponseResult<?> add(@NotNull SaveChannelDto saveChannelDto) {
        Integer ord = saveChannelDto.getOrd();
        if (ord == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "排序不能为空");
        } else if (ord < 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "排序不能小于0");
        }
        String name = saveChannelDto.getName();
        if (!StringUtils.hasLength(name)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称不能为空");
        } else if (!wmChannelMapper.selectList(new LambdaQueryWrapper<WmChannel>().eq(WmChannel::getName, name)).isEmpty()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称已存在");
        }
        Boolean isDefault = saveChannelDto.getIsDefault();
        Boolean status = saveChannelDto.getStatus();
        WmChannel wmChannel = WmChannel.builder()
                .name(name)
                .description(saveChannelDto.getDescription())
                .isDefault(isDefault != null ? isDefault : false)
                .status(status != null ? status : true)
                .createdTime(new Date())
                .build();
        if (wmChannelMapper.insert(wmChannel) == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
        return new ResponseResult<>().ok(200, wmChannel, "新增成功");
    }

    /**
     * 修改频道
     */
    @Override
    public ResponseResult<?> updateChannel(@NotNull UpdateChannelDto updateChannelDto) {
        Long id = updateChannelDto.getId();
        String name = updateChannelDto.getName();
        String description = updateChannelDto.getDescription();
        Integer ord = updateChannelDto.getOrd();
        if (ord != null && ord < 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "排序不能小于0");
        }
        Boolean status = updateChannelDto.getStatus();
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道id不能为空");
        }
        WmChannel wmChannel = wmChannelMapper.selectById(id);
        if (wmChannel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "频道不存在");
        }
        if (name != null) {
            wmChannel.setName(name);
        }
        if (description != null) {
            wmChannel.setDescription(description);
        }
        if (ord != null) {
            wmChannel.setOrd(ord);
        }
        if (status != null) {
            wmChannel.setStatus(status);
        }
        if (wmChannelMapper.updateById(wmChannel) == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
        return new ResponseResult<>().ok(200, wmChannel, "修改成功");
    }

    /**
     * 删除频道
     */
    @Override
    public ResponseResult<?> deleteChannel(@NotNull Long id) {
        if (wmChannelMapper.deleteById(id) == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
        return new ResponseResult<>().ok(200, AppHttpCodeEnum.SUCCESS, "删除成功");
    }
}
