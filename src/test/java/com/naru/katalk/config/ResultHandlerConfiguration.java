package com.naru.katalk.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

// @TestConfiguration을 @import 어노테이션을 사용하여 @TestConfiguration을 호출하는 클래스에서만 적용된다
// @Configuration은 모든 클래스에 적용된다
@TestConfiguration
public class ResultHandlerConfiguration {

    @Bean
    public RestDocumentationResultHandler restDocumentation() {
        return MockMvcRestDocumentation.document("{ClassName}/{methodName}");
    }
}
