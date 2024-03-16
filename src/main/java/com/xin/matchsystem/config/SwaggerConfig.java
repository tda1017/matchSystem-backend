package com.xin.matchsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * @author: TDA
 * @date: 2024/2/26 20:07
 * @description:
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //这里一定要标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.xin.matchsystem.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * api 信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("匹配系统")
                .description("http://localhost:8080/api/doc.html文档")
                .termsOfServiceUrl("https://github.com/tda1017")
                .contact(new Contact("tda1017","https://github.com/tda1017","1154379178@qq.com"))
                .version("1.0")
                .build();
    }
}
