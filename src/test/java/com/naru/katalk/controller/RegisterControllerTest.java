package com.naru.katalk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naru.katalk.common.ErrorCode;
import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.RegisterException;
import com.naru.katalk.service.RegisterService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.FieldDescriptorHelper.getDescriptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class)
class RegisterControllerTest extends ControllerTest {

    @MockBean
    private RegisterService registerService;

    private static final Class<SignManager> signManagerClass = SignManager.class;

    private static final Class<ProfileManager> profileManagerClass = ProfileManager.class;

    private static final Member member = Member.getTestInstance();

    @Test
    public void 회원가입_성공() throws Exception {

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users", member))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                fieldWithPath("memberId").ignored(),
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

    @Test
    public void 회원가입_실패_중복된_이메일() throws Exception {

        String email = member.getSignManager().getEmail();
        RegisterException registerException =
                new RegisterException("email", email, ErrorCode.EMAIL_DUPLICATION);
        doThrow(registerException).when(registerService).register(any());

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users", member))
                .andExpect(status().isConflict())
                .andDo(print())
                .andDo(restDocumentation.document());

    }
}