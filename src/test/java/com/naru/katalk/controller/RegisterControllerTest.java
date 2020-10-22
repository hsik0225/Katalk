package com.naru.katalk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.naru.katalk.config.ResultHandlerConfiguration;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.GuestService;
import com.naru.katalk.util.MockMvcPostHelper;
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

import static com.naru.katalk.util.TestAttribute.*;
import static org.mockito.BDDMockito.given;
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

    private static SignManager signManager;

    private static ProfileManager profileManager;

    private static MemberManager memberManager;

    @BeforeAll
    public static void setUp() {
        signManager = SignManager
                .builder()
                .email(EMAIL.getAttribute())
                .password(PASSWORD.getAttribute())
                .confirmPassword(CONFIRM_PASSWORD.getAttribute())
                .build();

        profileManager = ProfileManager
                .builder()
                .userName(USER_NAME.getAttribute())
                .picture(PICTURE.getAttribute())
                .build();

        memberManager = new MemberManager(signManager, profileManager);
    }

    @Test
    public void 회원가입_성공() throws Exception {

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users", memberManager))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("The user's email address"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("The user's password"),
                                fieldWithPath("confirmPassword").type(JsonFieldType.STRING).description("The user's confirm password"),
                                fieldWithPath("username").type(JsonFieldType.STRING).description("The name used chatting room"),
                                fieldWithPath("picture").type(JsonFieldType.STRING).description("The picture of profile")
                        )
                ));
    }
}