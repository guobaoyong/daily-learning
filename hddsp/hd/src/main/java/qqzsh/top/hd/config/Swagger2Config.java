package qqzsh.top.hd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:34
 * @description Swagger2配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Restful风格的接口
     * @return
     */
    @Bean
    public Docket createRestApi(){

        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder tokenPar2 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("headerUserId").description("用户ID").
                modelRef(new ModelRef("string")).
                parameterType("header").
                required(false).build();
        tokenPar2.name("headerUserToken").description("用户token").
                modelRef(new ModelRef("string")).
                parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        pars.add(tokenPar2.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("qqzsh.top.hd.controller"))
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(pars);
    }

    /**
     * 页面显示的开发者个人信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("嗨抖短视频API接口文档")
                .contact(new Contact("zsh","http://qqzsh.top","2016zsh@wlgzs.org"))
                .description("年轻人的欢乐短视频社区")
                .version("1.0").build();
    }
}
