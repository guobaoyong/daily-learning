package com.heima.wemedia.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.dto.WmLoginDto;
import com.heima.model.wemedia.vo.LoginVo;
import com.heima.wemedia.service.WmUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 高翔宇
 * @since 2024/2/15 周四 下午4:11
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "自媒体用户登录接口")
public class LoginController {
    private final WmUserService wmUserService;

    /**
     * 自媒体用户登录
     */
    @PostMapping("/in")
    @Operation(summary = "自媒体用户登录接口")
    public ResponseResult<LoginVo> login(@RequestBody WmLoginDto dto) {
        log.info("自媒体用户登录：{}", dto);
        return wmUserService.login(dto);
    }
}
