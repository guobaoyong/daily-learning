package com.heima.user.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.user.dto.ApUserRealnamePageQueryDto;
import com.heima.model.user.dto.ApUserRealnamePassDto;
import com.heima.model.user.dto.ApUserRealnameRejectDto;
import com.heima.model.user.entity.ApUserRealname;
import com.heima.user.service.ApUserRealnameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/15 周一 下午12:49
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class ApUserRealnameController {
    private final ApUserRealnameService apUserRealnameService;

    /**
     * 查询实名认证列表
     */
    @PostMapping("/list")
    @Operation(summary = "查询实名认证列表")
    public ResponseResult<List<ApUserRealname>> list(@RequestBody ApUserRealnamePageQueryDto apUserRealnameDto) {
        log.info("查询实名认证列表：{}", apUserRealnameDto);
        return apUserRealnameService.list(apUserRealnameDto);
    }

    /**
     * 实名认证通过
     */
    @PostMapping("/authPass")
    @Operation(summary = "实名认证通过")
    public ResponseResult<?> pass(@RequestBody ApUserRealnamePassDto apUserRealnamePassDto) {
        log.info("实名认证通过：{}", apUserRealnamePassDto);
        return apUserRealnameService.pass(apUserRealnamePassDto);
    }

    /**
     * 实名认证驳回
     */
    @PostMapping("/authFail")
    @Operation(summary = "实名认证驳回")
    public ResponseResult<?> reject(@RequestBody ApUserRealnameRejectDto apUserRealnameRejectDto) {
        log.info("实名认证驳回：{}", apUserRealnameRejectDto);
        return apUserRealnameService.reject(apUserRealnameRejectDto);
    }
}
