package com.heima.wemedia.service;

import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024/3/31 周日 下午5:07
 */
public interface WmNewsTaskService {
    /**
     * 添加图文消息到任务队列
     *
     * @param id          图文消息ID
     * @param publishTime 发布时间
     */
    void addNewsToTaskQueue(@NonNull Long id, Date publishTime);

    /**
     * 从任务队列中拉取任务，审核并发布文章
     */
    void scanNewsFromTaskQueue();
}
