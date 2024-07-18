package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.ChannelPageQueryDto;
import com.heima.model.wemedia.dto.SaveChannelDto;
import com.heima.model.wemedia.dto.UpdateChannelDto;
import com.heima.model.wemedia.entity.WmChannel;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024-02-20, 周二, 11:42
 */
public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有可用频道
     */
    ResponseResult<List<WmChannel>> listChannels();
    /**
     * 分页查询频道
     */
    ResponseResult<List<WmChannel>> pageQuery(@NonNull ChannelPageQueryDto channelPageQueryDto);

    /**
     * 新增频道
     */
    ResponseResult<?> add(@NonNull SaveChannelDto saveChannelDto);

    /**
     * 修改频道
     */
    ResponseResult<?> updateChannel(@NonNull UpdateChannelDto updateChannelDto);

    /**
     * 删除频道
     */
    ResponseResult<?> deleteChannel(@NotNull Long id);
}
