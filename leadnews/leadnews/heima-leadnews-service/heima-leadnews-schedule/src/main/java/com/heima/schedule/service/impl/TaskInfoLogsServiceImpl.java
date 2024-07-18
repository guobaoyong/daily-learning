package com.heima.schedule.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.schedule.entity.TaskinfoLogs;
import com.heima.schedule.mapper.TaskInfoLogsMapper;
import com.heima.schedule.service.TaskInfoLogsService;
import org.springframework.stereotype.Service;

/**
 * @author 高翔宇
 * @since 2024/3/30 周六 上午11:10
 */
@Service
public class TaskInfoLogsServiceImpl extends ServiceImpl<TaskInfoLogsMapper, TaskinfoLogs> implements TaskInfoLogsService {
}
