package com.naru.katalk.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;

// @TestConfiguration을 @import 어노테이션을 사용하여 @TestConfiguration을 호출하는 클래스에서만 적용된다
// @Configuration은 모든 클래스에 적용된다
@TestConfiguration
public class ResultHandlerConfiguration {

    @Bean
    public RestDocumentationResultHandler restDocumentation() {
        // RestDocs 스니펫 이름 설정 및 Request 와 Response 를 정리하여 출력
        return MockMvcRestDocumentation
                .document("{ClassName}/{methodName}",
                        preprocessRequest(
                                prettyPrint(),
                                removeHeaders("Host", "Content-Length")
                        ),
                        preprocessResponse(
                                prettyPrint(),
                                removeHeaders("Content-Length")
                        )
                );
    }
}
