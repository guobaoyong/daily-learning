package com.heima.demo.kafka.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author 高翔宇
 * @since 2024/4/3 周三 下午2:22
 */
@Data
@Builder
public class User {
    private String name;
    private Integer age;
}
