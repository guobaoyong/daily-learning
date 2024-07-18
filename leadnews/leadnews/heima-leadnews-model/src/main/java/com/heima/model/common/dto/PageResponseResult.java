package com.heima.model.common.dto;

import com.heima.model.common.enums.AppHttpCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @author 高翔宇
 * @since 2024/02/06 18:06:09
 */
@Schema(description = "分页响应体基本结构")
public class PageResponseResult<T> extends ResponseResult<T> implements Serializable {
    @Schema(description = "当前页")
    private Integer currentPage;
    @Schema(description = "每页显示条数")
    private Integer size;
    @Schema(description = "总条数")
    private Integer total;

    public PageResponseResult(Integer currentPage, Integer size, Integer total) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
    }

    public PageResponseResult() {

    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public PageResponseResult<T> ok(Integer code, T data) {
        this.setCode(code);
        this.setData(data);
        return this;
    }

    @Override
    public PageResponseResult<T> ok(Integer code, T data, String msg) {
        this.setCode(code);
        this.setData(data);
        this.setErrorMessage(msg);
        return this;
    }

    @Override
    public PageResponseResult<T> ok(T data) {
        this.setData(data);
        return this;
    }

    @Override
    public PageResponseResult<T> error(Integer code, String msg) {
        this.setCode(code);
        this.setErrorMessage(msg);
        return this;
    }

    public static <T> PageResponseResult<T> errorResult(AppHttpCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getErrorMessage());
    }

    public static <T> PageResponseResult<T> errorResult(AppHttpCodeEnum enums, String errorMessage) {
        return setAppHttpCodeEnum(enums, errorMessage);
    }

    public static <T> PageResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums) {
        return okResult(enums.getCode(), enums.getErrorMessage());
    }

    private static <T> PageResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums, String errorMessage) {
        return okResult(enums.getCode(), errorMessage);
    }

    public static <T> PageResponseResult<T> errorResult(int code, String msg) {
        PageResponseResult<T> result = new PageResponseResult<>();
        return result.error(code, msg);
    }

    public static <T> PageResponseResult<T> okResult(int code, String msg) {
        PageResponseResult<T> result = new PageResponseResult<>();
        return result.ok(code, null, msg);
    }

    public static <T> PageResponseResult<T> okResult(T data) {
        PageResponseResult<T> result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getErrorMessage());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }
}
