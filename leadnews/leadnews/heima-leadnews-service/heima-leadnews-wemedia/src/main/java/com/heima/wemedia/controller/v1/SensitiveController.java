package com.heima.wemedia.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.AddSensitiveDto;
import com.heima.model.wemedia.dto.SensitiveQueryDto;
import com.heima.model.wemedia.dto.UpdateSensitiveDto;
import com.heima.model.wemedia.entity.WmSensitive;
import com.heima.wemedia.service.SensitiveService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午3:36
 */
@RestController
@RequestMapping("/api/v1/sensitive")
@Slf4j
@RequiredArgsConstructor
public class SensitiveController {
    private final SensitiveService sensitiveService;

    @PostMapping("/save")
    @Operation(summary = "添加敏感词")
    public ResponseResult<?> add(@RequestBody AddSensitiveDto addSensitiveDto){
        log.info("接收到敏感词添加请求：{}", addSensitiveDto);
        return sensitiveService.add(addSensitiveDto);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除敏感词")
    public ResponseResult<?> delete(@PathVariable Long id) {
        log.info("接收到敏感词删除请求：{}", id);
        return sensitiveService.delete(id);
    }

    @PostMapping("/update")
    @Operation(summary = "更新敏感词")
    public ResponseResult<?> update(@RequestBody UpdateSensitiveDto updateSensitiveDto){
        log.info("接收到敏感词更新请求：{}", updateSensitiveDto);
        return sensitiveService.update(updateSensitiveDto);
    }

    @PostMapping("/list")
    @Operation(summary = "查询敏感词")
    public ResponseResult<List<WmSensitive>> list(@RequestBody SensitiveQueryDto sensitiveQueryDto) {
        log.info("接收到敏感词查询请求：{}", sensitiveQueryDto);
        return sensitiveService.list(sensitiveQueryDto);
    }
}
