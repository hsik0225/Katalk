package com.naru.katalk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.LoginService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.FieldDescriptorHelper.getDescriptor;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest extends ControllerTest {

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

