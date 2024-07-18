package com.heima.common.aliyun.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024/2/29 周四 下午3:16
 */
@Slf4j
public class OssFileUploader {
    String endpoint = """
            https://oss-cn-hangzhou.aliyuncs.com""";
    String bucketName = "heima-leadnews-fallingdust";
    String ossAccessKeyId = "LTAI5tSTnrNJSy6fH7XvteWi";
    String ossAccessKeySecret = "CdbXZN8ZWVIRvd2WYP5qxy1r40yzX8";

    public String upload(byte[] bytes, String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, ossAccessKeyId, ossAccessKeySecret);
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
            System.out.println(putObjectResult);
            // 获取文件访问路径，需先获取token
            return ossClient.generatePresignedUrl(bucketName, objectName, new Date(System.currentTimeMillis() + 3600 * 1000)).toString();
        } catch (OSSException oe) {
            log.error("捕获到OSSException，这意味着您的请求发送到OSS，但由于某种原因被拒绝并返回错误响应。");
            System.out.println("错误消息：" + oe.getErrorMessage());
            System.out.println("错误代码：" + oe.getErrorCode());
            System.out.println("请求ID：" + oe.getRequestId());
            System.out.println("主机ID：" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("捕获到ClientException，这意味着客户端在尝试与OSS通信时遇到了严重的内部问题，例如不能接入网络。");
            System.out.println("错误消息：" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
