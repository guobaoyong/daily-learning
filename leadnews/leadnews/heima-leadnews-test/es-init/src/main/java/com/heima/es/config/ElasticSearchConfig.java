package com.heima.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @author itheima，高翔宇
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Slf4j
public class ElasticSearchConfig {
    private String serverUrl;
    private String apiKey;

    @Bean
    public ElasticsearchClient client() {
        try {
            SSLContext sslContext = SSLContexts.custom()
                    // 装入密钥库。密钥库是一个文件，“fallingdust”是密钥库的密码
                    .loadTrustMaterial(new File("D:\\BaiduSyncdisk\\myCode\\Projects\\黑马头条\\heima-leadnews\\heima-leadnews-test\\es-init\\src\\main\\resources\\keystore.jks"), "fallingdust".toCharArray())
                    .build();
            RestClient restClient = RestClient
                    .builder(HttpHost.create(serverUrl))
                    .setDefaultHeaders(new Header[]{
                            new BasicHeader("Authorization", "ApiKey " + apiKey)
                    }).setHttpClientConfigCallback(httpClientBuilder ->
                            httpClientBuilder.setSSLContext(sslContext))
                    .build();
            // 使用 Jackson 映射器创建传输
            ElasticsearchTransport transport = new RestClientTransport(
                    restClient, new JacksonJsonpMapper());

            // 并创建 API 客户端
            return new ElasticsearchClient(transport);
        } catch (NoSuchAlgorithmException | KeyManagementException | IOException | KeyStoreException |
                 CertificateException e) {
            log.error("无法创建 Elasticsearch 客户端", e);
        }
        return null;
    }
}
