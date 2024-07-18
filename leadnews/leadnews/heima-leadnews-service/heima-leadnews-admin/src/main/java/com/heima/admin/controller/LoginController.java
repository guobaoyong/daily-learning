package com.heima.admin.controller;

import com.heima.admin.service.AdLoginService;
import com.heima.model.admin.dto.AdLoginDto;
import com.heima.model.admin.vo.AdLoginVo;
import com.heima.model.common.dto.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:30
 */
@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final AdLoginService adLoginService;

    @PostMapping("/in")
    @Operation(summary = "登录")
    public ResponseResult<AdLoginVo> login(@NonNull @RequestBody AdLoginDto adLoginDto) {
        log.info("login: {}", adLoginDto);
        return adLoginService.login(adLoginDto);
    }
}
