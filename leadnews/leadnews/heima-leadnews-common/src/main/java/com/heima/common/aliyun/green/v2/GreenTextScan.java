package com.heima.common.aliyun.green.v2;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.green20220302.Client;
import com.aliyun.green20220302.models.TextModerationRequest;
import com.aliyun.green20220302.models.TextModerationResponse;
import com.aliyun.green20220302.models.TextModerationResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.heima.common.aliyun.green.v2.util.AliYunProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 文本检测
 *
 * @author 高翔宇
 * @since 2024/2/28 周三 下午2:31
 */
@Slf4j
public class GreenTextScan {
    @Autowired
    private AliYunProperties aliYunProperties;
    String endpoint = "green-cip.cn-shanghai.aliyuncs.com";
    String regionId = "cn-shanghai";
    private Client client;

    @PostConstruct
    private void init() throws Exception {
        creatClient();
    }

    private void creatClient() throws Exception {
        Config config = new Config();
        config.setAccessKeyId(aliYunProperties.getAccessKeyId());
        config.setAccessKeySecret(aliYunProperties.getAccessKeySecret());
        config.setRegionId(regionId);
        config.setEndpoint(endpoint);
        //连接时超时时间，单位毫秒（ms）。
        config.setReadTimeout(6000);
        //读取时超时时间，单位毫秒（ms）。
        config.setConnectTimeout(3000);
        client = new Client(config);
    }

    /**
     * 文本检测
     *
     * @param text        文本内容
     * @param serviceCode 服务code，内容安全控制台文本增强版规则配置的serviceCode，示例：chat_detection，ai_art_detection等
     * @return 检测结果
     */
    public Map<String, String> checkText(String text, String serviceCode) {
        // 创建RuntimeObject实例并设置运行参数。
        RuntimeOptions runtime = new RuntimeOptions();
        runtime.readTimeout = 10000;
        runtime.connectTimeout = 10000;

        //检测参数构造
        JSONObject serviceParameters = new JSONObject();
        serviceParameters.put("content", text);

        if (serviceParameters.get("content") == null || serviceParameters.getString("content").trim().isEmpty()) {
            System.out.println("text moderation content is empty");
            return null;
        }

        TextModerationRequest textModerationRequest = new TextModerationRequest();
        /*
        文本检测service：内容安全控制台文本增强版规则配置的serviceCode，示例：chat_detection
        */
        textModerationRequest.setService(serviceCode);
        textModerationRequest.setServiceParameters(serviceParameters.toJSONString());
        try {
            // 调用方法获取检测结果。
            TextModerationResponse response = client.textModerationWithOptions(textModerationRequest, runtime);

            // 自动路由。
            if (response != null) {
                // 服务端错误，区域切换到cn-beijing。
                if (500 == response.getStatusCode() || (response.getBody() != null && 500 == (response.getBody().getCode()))) {
                    // 接入区域和地址请根据实际情况修改。
                    regionId = "cn-beijing";
                    endpoint = "green-cip.cn-beijing.aliyuncs.com";
                    creatClient();
                    response = client.textModerationWithOptions(textModerationRequest, runtime);
                }

            }
            if (response != null) {
                if (response.getStatusCode() == 200) {
                    TextModerationResponseBody result = response.getBody();
                    System.out.println(JSON.toJSONString(result));
                    Integer code = result.getCode();
                    if (code != null && code == 200) {
                        TextModerationResponseBody.TextModerationResponseBodyData data = result.getData();
                        return Map.of("code", code.toString(), "labels", data.getLabels(), "reason", data.getReason(), "message", result.getMessage());
                    } else {
                        System.out.println("text moderation not success. code:" + code);
                    }
                } else {
                    System.out.println("response not success. status:" + response.getStatusCode());
                }
            }
        } catch (Exception e) {
            log.error("文本检测失败", e);
        }
        return null;
    }
}
