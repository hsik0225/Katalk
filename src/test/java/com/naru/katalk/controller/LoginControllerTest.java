package com.naru.katalk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.naru.katalk.config.ResultHandlerConfiguration;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.advice.UserControllerAdvice;
import com.naru.katalk.service.LoginService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.FieldDescriptorHelper.getDescriptor;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})

/*
MockMvc 를 주입받는 2가지 방법
1. @SpringBootTest + @AutoConfigureMockMvc
2. @WebMvcTest
 */
@WebMvcTest(controllers = {LoginController.class, UserControllerAdvice.class})

@Import(ResultHandlerConfiguration.class)

// 디렉토리 경로를 인수로 받아 결과 디렉토리(outputDir)로 설정한다
// build.gradle 의 설정을 디폴트로 갖는다
@AutoConfigureRestDocs
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserControllerAdvice userControllerAdvice;

    @Autowired
    private RestDocumentationResultHandler restDocumentation;

    @MockBean
    private LoginService loginService;

    @Test
    public void 로그인() throws Exception {

        final SignManager signManager = SignManager.getTestInstance();

        final Class<SignManager> signManagerClass = SignManager.class;

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users/login", signManager))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                getDescriptor("email", "이메일", signManagerClass),
                                getDescriptor("password", "비밀번호", signManagerClass),
                                getDescriptor("confirmPassword", "비밀번호 확인, 로그인에서는 무시되는 속성입니다")
                                        .optional()
                        )
                ));
    }
}

