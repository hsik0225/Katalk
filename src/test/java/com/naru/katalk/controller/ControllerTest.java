package com.naru.katalk.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.naru.katalk.config.ResultHandlerConfiguration;

/*
MockMvc 를 주입받는 2가지 방법
1. @SpringBootTest + @AutoConfigureMockMvc
2. @WebMvcTest
 */
@WebMvcTest(controllers = RegisterController.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@Import({ResultHandlerConfiguration.class})
@AutoConfigureRestDocs
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected RestDocumentationResultHandler restDocumentation;
}
