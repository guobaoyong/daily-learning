package com.heima.common.aliyun.green.v1.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author itheima
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadCredentials implements Serializable {
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private Long expiredTime;
    private String ossEndpoint;
    private String ossInternalEndpoint;
    private String uploadBucket;
    private String uploadFolder;
}
