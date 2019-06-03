package com.openpay.paywebapi.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket userApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("token").description("token信息")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false);
        pars.add(ticketPar.build());
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .groupName("快递相关接口") //接口模块名称
                // 是否使用默认的ResponseMessage， 框架默认定义了一些针对各个HTTP方法时各种不同响应值对应的message
                .useDefaultResponseMessages(false)
                // 遇到对应泛型类型的外围类，直接解析成泛型类型，比如说ResponseEntity<T>，应该直接输出成类型T
                .genericModelSubstitutes(ResponseEntity.class)
                // 自定义性更强的针对泛型的处理，将类型DeferredResult直接解析成类型T
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.openpay.paywebapi"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
        return docket;
    }

    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfoBuilder().title("缴费平台api").version("v.1.0.0").build();
        return apiInfo;
    }
}
