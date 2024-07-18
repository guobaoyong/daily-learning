package com.heima.common.exception;

import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.Getter;

/**
 * @author 高翔宇
 * @since 2024-02-6, 周二, 15:8
 */
@Getter
public class CustomException extends RuntimeException {

    private final AppHttpCodeEnum appHttpCodeEnum;

    public CustomException(AppHttpCodeEnum appHttpCodeEnum) {
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

}
