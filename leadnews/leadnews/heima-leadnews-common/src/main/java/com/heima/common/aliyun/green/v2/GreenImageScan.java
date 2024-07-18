package com.heima.common.aliyun.green.v2;

import com.alibaba.fastjson2.JSON;
import com.aliyun.green20220302.Client;
import com.aliyun.green20220302.models.*;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.heima.common.aliyun.green.v2.util.AliYunProperties;
import com.heima.common.aliyun.oss.OssFileUploader;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 图片检测
 *
 * @author 高翔宇
 * @since 2024/2/28 周三 下午2:31
 */
@Slf4j
public class GreenImageScan {
    @Autowired
    private AliYunProperties aliYunProperties;
    @Autowired
    private OssFileUploader ossFileUploader;
    private Client client;
    // 备用服务地址
    private final String backupEndpoint = "green-cip.cn-hangzhou.aliyuncs.com";

    // 服务是否部署在vpc上
    public boolean isVpc = false;
    // 3文件上传token endpoint->token
    public Map<String, DescribeUploadTokenResponseBody.DescribeUploadTokenResponseBodyData> tokenMap = new HashMap<>();
    // 上传文件请求客户端
    public OSS ossClient = null;

    @PostConstruct
    public void init() throws Exception {
        String endpoint = "green-cip.cn-beijing.aliyuncs.com";
        client = createClient(endpoint);
        System.out.println(client);
    }

    /**
     * 创建请求客户端
     */
    private Client createClient(String endpoint) throws Exception {
        Config config = new Config();
        config.setAccessKeyId(aliYunProperties.getAccessKeyId());
        config.setAccessKeySecret(aliYunProperties.getAccessKeySecret());
        config.setEndpoint(endpoint);
        return new Client(config);
    }

    /**
     * 创建上传文件请求客户端
     */
    private void getOssClient(DescribeUploadTokenResponseBody.DescribeUploadTokenResponseBodyData tokenData) {
        //注意，此处实例化的client请尽可能重复使用，避免重复建立连接，提升检测性能。
        if (isVpc) {
            ossClient = new OSSClientBuilder().build(tokenData.ossInternalEndPoint, tokenData.getAccessKeyId(), tokenData.getAccessKeySecret(), tokenData.getSecurityToken());
        } else {
            ossClient = new OSSClientBuilder().build(tokenData.ossInternetEndPoint, tokenData.getAccessKeyId(), tokenData.getAccessKeySecret(), tokenData.getSecurityToken());
        }
    }

    /**
     * 上传文件
     */
    private String uploadFile(String filePath, DescribeUploadTokenResponseBody.DescribeUploadTokenResponseBodyData tokenData) {
        String[] split = filePath.split("\\.");
        String objectName;
        if (split.length > 1) {
            objectName = tokenData.getFileNamePrefix() + UUID.randomUUID() + "." + split[split.length - 1];
        } else {
            objectName = tokenData.getFileNamePrefix() + UUID.randomUUID();
        }
        PutObjectRequest putObjectRequest = new PutObjectRequest(tokenData.getBucketName(), objectName, new File(filePath));
        ossClient.putObject(putObjectRequest);
        return objectName;
    }

    /**
     * 图片检测
     *
     * @param filePath    图片地址
     * @param serviceCode 服务code，内容安全控制台图片增强版规则配置的serviceCode，示例：baselineCheck。<a href="https://help.aliyun.com/document_detail/467826.html?0#p-23b-o19-gff">支持的serviceCode</a>
     */
    public ImageModerationResponse sendRequestWithFilePath(String filePath, String serviceCode) throws Exception {
        //注意，此处实例化的client请尽可能重复使用，避免重复建立连接，提升检测性能。
        RuntimeOptions runtime = new RuntimeOptions();
        runtime.readTimeout = 10000;
        runtime.connectTimeout = 10000;

        String bucketName = null;
        String endpoint = "green-cip.cn-beijing.aliyuncs.com";
        DescribeUploadTokenResponseBody.DescribeUploadTokenResponseBodyData uploadToken = tokenMap.get(endpoint);
        //获取文件上传token
        if (uploadToken == null || uploadToken.expiration <= System.currentTimeMillis() / 1000) {
            DescribeUploadTokenResponse tokenResponse = client.describeUploadToken();
            uploadToken = tokenResponse.getBody().getData();
            bucketName = uploadToken.getBucketName();
        }
        //上传文件请求客户端
        getOssClient(uploadToken);

        //上传文件
        String objectName = uploadFile(filePath, uploadToken);
        // 检测参数构造。
        Map<String, String> serviceParameters = new HashMap<>();
        //文件上传信息
        serviceParameters.put("ossBucketName", bucketName);
        serviceParameters.put("ossObjectName", objectName);
        serviceParameters.put("dataId", UUID.randomUUID().toString());

        ImageModerationRequest request = new ImageModerationRequest();
        request.setService(serviceCode);
        request.setServiceParameters(JSON.toJSONString(serviceParameters));

        ImageModerationResponse response = null;
        try {
            response = client.imageModerationWithOptions(request, runtime);
        } catch (Exception e) {
            log.error("图片检测失败", e);
        }
        return response;
    }

    /**
     * @param url         图片地址
     * @param serviceCode 服务code，内容安全控制台图片增强版规则配置的serviceCode，示例：baselineCheck。<a href="https://help.aliyun.com/document_detail/467826.html?0#p-23b-o19-gff">支持的serviceCode</a>
     * @return {@link ImageModerationResponse}
     */
    private ImageModerationResponse sendRequestWithUrl(String url, String serviceCode) throws Exception {
        // 创建RuntimeObject实例并设置运行参数
        RuntimeOptions runtime = new RuntimeOptions();

        // 检测参数构造。
        Map<String, String> serviceParameters = new HashMap<>();
        //公网可访问的URL。
        serviceParameters.put("imageUrl", url);
        //待检测数据唯一标识
        serviceParameters.put("dataId", UUID.randomUUID().toString());

        ImageModerationRequest request = new ImageModerationRequest();
        request.setService(serviceCode);
        request.setServiceParameters(JSON.toJSONString(serviceParameters));

        ImageModerationResponse response;
        response = client.imageModerationWithOptions(request, runtime);
        return response;
    }

    /**
     * 图片检测
     *
     * @param url         图片地址
     * @param serviceCode 服务code，内容安全控制台图片增强版规则配置的serviceCode，示例：baselineCheck。<a href="https://help.aliyun.com/document_detail/467826.html?0#p-23b-o19-gff">支持的serviceCode</a>
     * @return 检测结果
     */
    public Map<String, String> checkImageByUrl(String url, String serviceCode) {
        try {
            // 接入区域和地址请根据实际情况修改。
            ImageModerationResponse response = sendRequestWithUrl(url, serviceCode);
            // 自动路由。
            if (response != null) {
                // 区域切换到cn-beijing。
                if (500 == response.getStatusCode() || (response.getBody() != null && 500 == (response.getBody().getCode()))) {
                    // 接入区域和地址请根据实际情况修改。
                    client = createClient(backupEndpoint);
                    response = sendRequestWithUrl(url, serviceCode);
                }
            }

            // 打印检测结果。
            return disposeResponse(response);
        } catch (Exception e) {
            log.error("图片检测失败", e);
        }
        return null;
    }

    /**
     * 图片检测
     *
     * @param filePath    图片地址
     * @param serviceCode 服务code，内容安全控制台图片增强版规则配置的serviceCode，示例：baselineCheck。<a href="https://help.aliyun.com/document_detail/467826.html?0#p-23b-o19-gff">支持的serviceCode</a>
     * @return 检测结果
     */
    public Map<String, String> checkImageByFilePath(String filePath, String serviceCode) {
        try {
            ImageModerationResponse response = sendRequestWithFilePath(filePath, serviceCode);
            // 自动路由。
            if (response != null) {
                if (500 == response.getStatusCode() || (response.getBody() != null && 500 == (response.getBody().getCode()))) {
                    client = createClient(backupEndpoint);
                    response = sendRequestWithFilePath(filePath, serviceCode);
                }
            }
            // 打印检测结果。
            if (response != null) {
                return disposeResponse(response);
            }
        } catch (Exception e) {
            log.error("图片检测失败", e);
        }
        return null;
    }

    /**
     * 处理检测结果
     *
     * @param response {@link ImageModerationResponse} 检测响应对象
     * @return 检测结果
     */
    private @Nullable Map<String, String> disposeResponse(ImageModerationResponse response) {
        if (response != null) {
            if (response.getStatusCode() == 200) {
                ImageModerationResponseBody body = response.getBody();
                Integer code = body.getCode();
                if (code == 200) {
                    ImageModerationResponseBody.ImageModerationResponseBodyData data = body.getData();
                    System.out.println("dataId=" + data.getDataId());
                    List<ImageModerationResponseBody.ImageModerationResponseBodyDataResult> results = data.getResult();
                    Map<String, String> map = new HashMap<>();
                    if ("nonLabel".equals(results.get(0).getLabel())) {
                        map.put("label", "nonLabel");
                    } else {
                        results.forEach(result -> map.put(result.getLabel(), result.getConfidence() != null ? String.valueOf(result.getConfidence()) : null));
                    }
                    log.info("image moderation was successful. body: {}", JSON.toJSONString(body));
                    return Map.of("code", code.toString(), "message", body.getMsg(), "results", JSON.toJSONString(map), "dataId", data.getDataId());
                } else {
                    log.error("image moderation was not successful. body: {}", JSON.toJSONString(body));
                }
            } else {
                log.error("the response was unsuccessful. statusCode: {}, body: {}", response.getStatusCode(), JSON.toJSONString(response.getBody()));
            }
        }
        return null;
    }

    /**
     * 图片检测
     *
     * @param bytes       图片字节数组
     * @param serviceCode 服务code，内容安全控制台图片增强版规则配置的serviceCode，示例：baselineCheck。<a href="https://help.aliyun.com/document_detail/467826.html?0#p-23b-o19-gff">支持的serviceCode</a>
     * @return 检测结果
     */
    public Map<String, String> checkImageByBytes(byte[] bytes, String serviceCode) {
        try {
            ImageModerationResponse response = sendRequestWithBytes(bytes, UUID.randomUUID().toString(), serviceCode);
            // 自动路由。
            if (response != null) {
                if (500 == response.getStatusCode() || (response.getBody() != null && 500 == (response.getBody().getCode()))) {
                    client = createClient(backupEndpoint);
                    response = sendRequestWithBytes(bytes, UUID.randomUUID().toString(), serviceCode);
                }
            }
            if (response != null) {
                return disposeResponse(response);
            }
        } catch (Exception e) {
            log.error("图片检测失败", e);
        }
        return null;
    }

    private ImageModerationResponse sendRequestWithBytes(byte[] bytes, String objectName, String serviceCode) throws Exception {
        String url = ossFileUploader.upload(bytes, objectName);
        return sendRequestWithUrl(url, serviceCode);
    }
}