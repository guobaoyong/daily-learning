package com.heima.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author 高翔宇
 * @since 2024-02-7, 周三, 19:43
 */
@SpringBootConfiguration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title("黑马头条项目API文档")
                        .description("黑马头条项目API文档")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://gitee.com/a2176281647/heima-leadnews.git")))
                .externalDocs(new ExternalDocumentation()
                        .description("黑马头条项目文档")
                        .url("敬请期待~"))
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
