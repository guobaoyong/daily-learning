package com.heima.article.components;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.stereotype.Component;

/**
 * 自定义ID生成器
 *
 * @author 高翔宇
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        // 可以将当前传入的class全类名来作为bizKey，或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        IdWorker.setIdentifierGenerator(new DefaultIdentifierGenerator(0, 0));
        // 根据bizKey调用分布式ID生成
        return IdWorker.getId(bizKey);
    }
}