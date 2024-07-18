package com.heima.user.controller.v1;

import com.heima.model.common.dto.ResponseResult;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.vo.LoginVo;
import com.heima.user.service.ApUserService;
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
 * @since 2024/2/7 周三 12:23
 */
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "APP登录接口", description = "APP登录接口")
public class ApUserLoginController {
    private final ApUserService apUserService;

    @PostMapping("/login_auth")
    @Operation(summary = "APP用户登录接口", description = "支持游客登录，字段值为空时表示游客登录")
    public ResponseResult<LoginVo> login(@RequestBody LoginDto loginDto) {
        log.info("登录：{}", loginDto);
        return apUserService.login(loginDto);
    }
}
