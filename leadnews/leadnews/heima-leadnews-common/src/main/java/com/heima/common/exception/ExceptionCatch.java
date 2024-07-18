package com.heima.common.exception;


import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器增强类
 *
 * @author 高翔宇
 * @since 2024-02-6, 周二, 15:9
 */
@ControllerAdvice
@Slf4j
public class ExceptionCatch {

    /**
     * 处理不可控异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<Object> exception(Exception e) {
        // e.printStackTrace();
        log.error("catch exception:{}", e.getMessage(), e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
    }

    /**
     * 处理可控异常  自定义异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult<Object> exception(CustomException e) {
        log.error("捕获异常：{}", e.getMessage(), e);
        return ResponseResult.errorResult(e.getAppHttpCodeEnum());
    }
}
