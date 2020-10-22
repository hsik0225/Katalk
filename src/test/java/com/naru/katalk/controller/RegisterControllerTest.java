package com.naru.katalk.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.naru.katalk.config.ResultHandlerConfiguration;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.GuestService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.TestAttribute.CONFIRM_PASSWORD;
import static com.naru.katalk.util.TestAttribute.EMAIL;
import static com.naru.katalk.util.TestAttribute.PASSWORD;
import static com.naru.katalk.util.TestAttribute.PICTURE;
import static com.naru.katalk.util.TestAttribute.USER_NAME;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(RegisterController.class)
@Import(ResultHandlerConfiguration.class)
@AutoConfigureRestDocs
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestDocumentationResultHandler restDocumentation;

    @MockBean
    private GuestService guestService;

    private static MemberManager memberManager;

    @BeforeAll
    public static void setUp() {
        SignManager signManager = SignManager
                .builder()
                .email(EMAIL.getAttribute())
                .password(PASSWORD.getAttribute())
                .confirmPassword(CONFIRM_PASSWORD.getAttribute())
                .build();

        ProfileManager profileManager = ProfileManager
                .builder()
                .userName(USER_NAME.getAttribute())
                .picture(PICTURE.getAttribute())
                .build();

        memberManager = new MemberManager(signManager, profileManager);
    }

    @Test
    public void 회원가입() throws Exception {

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users", memberManager))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                fieldWithPath("signManager.email").type(JsonFieldType.STRING)
                                        .description("이메일"),
                                fieldWithPath("signManager.password").type(JsonFieldType.STRING)
                                        .description("비밀번호"),
                                fieldWithPath("signManager.confirmPassword")
                                        .type(JsonFieldType.STRING).description("비밀번호 확인"),
                                fieldWithPath("profileManager.userName").type(JsonFieldType.STRING)
                                        .description("채팅방에서 사용하는 대화명"),
                                fieldWithPath("profileManager.picture").type(JsonFieldType.STRING)
                                        .description("채팅방에서 사용할 사진 URL")
                        )
                ));
    }
}