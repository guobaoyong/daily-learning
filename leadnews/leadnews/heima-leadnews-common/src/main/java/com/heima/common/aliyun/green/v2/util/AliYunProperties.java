package com.heima.common.aliyun.green.v2.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 高翔宇
 * @since 2024/2/29 周四 下午3:19
 */
@ConfigurationProperties(prefix = "aliyun")
@Data
public class AliYunProperties {
    private String accessKeyId;
    private String accessKeySecret;
}
