package com.heima.model.schedule.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author itheima
 */
@Getter
@AllArgsConstructor
public enum TaskTypeEnum {

    NEWS_SCAN_TIME(1001, 1, "文章定时审核"),
    REMOTE_ERROR(1002, 2, "第三方接口调用失败，重试");

    // 对应具体业务
    private final int taskType;
    // 业务不同级别
    private final int priority;
    // 描述信息
    private final String desc;
}