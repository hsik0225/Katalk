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
import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.RegisterService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.FieldDescriptorHelper.getDescriptor;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegisterControllerTest extends ControllerTest {

    @MockBean
    private RegisterService registerService;

    private static final Class<SignManager> signManagerClass = SignManager.class;

    private static final Class<ProfileManager> profileManagerClass = ProfileManager.class;

    @Test
    public void 회원가입() throws Exception {

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users", Member.getTestInstance()))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                getDescriptor("signManager.email", "이메일",
                                        signManagerClass),
                                getDescriptor("signManager.password", "비밀번호",
                                        signManagerClass),
                                getDescriptor("signManager.confirmPassword", "비밀번호 확인",
                                        "Must match the password"),
                                getDescriptor("profileManager.chatName", "대화명",
                                        profileManagerClass),
                                getDescriptor("profileManager.picture", "사진").optional()
                        )
                ));
    }


}