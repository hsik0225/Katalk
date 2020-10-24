package com.naru.katalk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.naru.katalk.config.ResultHandlerConfiguration;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.GuestService;
import com.naru.katalk.util.MockMvcPostHelper;

import static com.naru.katalk.util.FieldDescriptorHelper.getConstraintDescriptor;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(controllers = RegisterController.class)
@Import({ResultHandlerConfiguration.class})
@AutoConfigureRestDocs
class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestDocumentationResultHandler restDocumentation;

    @MockBean
    private GuestService guestService;

    @Test
    public void 회원가입() throws Exception {

        MemberManager memberManager = MemberManager.getTestInstance();

        ConstraintDescriptions signManagerDescriptor =
                new ConstraintDescriptions(SignManager.class);
        ConstraintDescriptions profileManagerDescriptor =
                new ConstraintDescriptions(ProfileManager.class);

        this.mockMvc
                .perform(MockMvcPostHelper.postObject("/users", memberManager))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(restDocumentation.document(
                        requestFields(
                                getConstraintDescriptor("signManager.email", "이메일",
                                        SignManager.class),
                                getConstraintDescriptor("signManager.password", "비밀번호",
                                        SignManager.class),
                                getConstraintDescriptor("signManager.confirmPassword", "비밀번호 확인",
                                        SignManager.class),
                                getConstraintDescriptor("profileManager.chatName", "대화명",
                                        ProfileManager.class),
                                getConstraintDescriptor("profileManager.picture", "사진",
                                        ProfileManager.class)
                        )
                ));
    }


}