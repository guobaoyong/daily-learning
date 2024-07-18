package com.heima.file.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author itheima，高翔宇
 * @since 2024-02-14, 周三, 16:5
 */
@Data
@ConfigurationProperties(prefix = "minio")  // 文件上传 配置前缀file.oss
public class MinIOConfigProperties implements Serializable {
    private String accessKey;
    private String secretKey;
    /**
     * bucket名称
     */
    private String bucket;
    /**
     * endpoint是服务器的访问地址
     */
    private String endpoint;
    /**
     * readPath是文件的访问路径
     */
    private String readPath;
}
