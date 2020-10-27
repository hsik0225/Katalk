package com.naru.katalk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.LoginException;
import com.naru.katalk.service.LoginService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.FieldDescriptorHelper.getDescriptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class LoginControllerTest extends ControllerTest {

    @MockBean
    private LoginService loginService;

    private static final SignManager SIGN_MANAGER = SignManager.getTestInstance();

    public static final String URL = "/users/login";

    @Test
    public void 로그인_성공() throws Exception {

        final Class<SignManager> signManagerClass = SignManager.class;

        this.mockMvc
                .perform(MockMvcPostHelper.postObject(URL, SIGN_MANAGER))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                getDescriptor("email", "이메일", signManagerClass),
                                getDescriptor("password", "비밀번호", signManagerClass),
                                getDescriptor("confirmPassword", "비밀번호 확인. 로그인에서는 무시되는 속성입니다")
                                        .optional()
                        )
                ));
    }

    @Test
    public void 로그인_실패() throws Exception {

        doThrow(LoginException.class).when(loginService).login(any());

        this.mockMvc
                .perform(MockMvcPostHelper.postObject(URL, SIGN_MANAGER))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andDo(restDocumentation.document());
    }
}

