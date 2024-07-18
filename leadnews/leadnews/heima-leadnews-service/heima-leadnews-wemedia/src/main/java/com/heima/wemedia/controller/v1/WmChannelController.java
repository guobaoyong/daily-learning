package com.heima.wemedia.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.ChannelPageQueryDto;
import com.heima.model.wemedia.dto.SaveChannelDto;
import com.heima.model.wemedia.dto.UpdateChannelDto;
import com.heima.model.wemedia.entity.WmChannel;
import com.heima.wemedia.service.WmChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/20 周二 上午11:49
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/v1/channel")
@Tag(name = "自媒体频道管理接口")
@RequiredArgsConstructor
public class WmChannelController {
    private final WmChannelService wmChannelService;

    /**
     * 查询所有可用频道
     */
    @Operation(summary = "查询所有频道")
    @GetMapping("/channels")
    public ResponseResult<List<WmChannel>> listChannels() {
        log.info("查询所有（启用的）频道");
        return wmChannelService.listChannels();
    }

    /**
     * 分页查询频道
     */
    @PostMapping("/list")
    @Operation(summary = "分页查询频道")
    public ResponseResult<List<WmChannel>> pageQuery(@RequestBody ChannelPageQueryDto channelPageQueryDto) {
        log.info("分页查询频道，{}", channelPageQueryDto);
        return wmChannelService.pageQuery(channelPageQueryDto);
    }

    /**
     * 新增频道
     */
    @PostMapping("/save")
    @Operation(summary = "新增频道")
    public ResponseResult<?> add(@RequestBody SaveChannelDto saveChannelDto) {
        log.info("新增频道，{}", saveChannelDto);
        return wmChannelService.add(saveChannelDto);
    }

    /**
     * 修改频道
     */
    @PostMapping("/update")
    @Operation(summary = "修改频道")
    public ResponseResult<?> update(@RequestBody UpdateChannelDto updateChannelDto) {
        log.info("修改频道，{}", updateChannelDto);
        return wmChannelService.updateChannel(updateChannelDto);
    }

    /**
     * 删除频道
     */
    @GetMapping("/del/{id}")
    @Operation(summary = "删除频道")
    public ResponseResult<?> delete(@PathVariable("id") Long id) {
        log.info("删除频道，{}", id);
        return wmChannelService.deleteChannel(id);
    }
}
