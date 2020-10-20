package com.naru.katalk.controller;

import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.GuestService;
import com.naru.katalk.util.ObjectToJsonConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(GuestController.class)

// 디렉토리 경로를 인수로 받아 결과 디렉토리(outputDir)로 설정한다
// build.gradle 의 설정을 디폴트로 갖는다
@AutoConfigureRestDocs
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GuestService guestService;

    @Test
    public void 로그인_성공() throws Exception {

        SignManager signManager = SignManager.getTestInstance();
        String jsonSignManager = ObjectToJsonConverter.objectToJson(signManager);

        // when
        this.mockMvc
                .perform(
                        post("/users/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonSignManager)
                                .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andDo(document(
                        "login",
                        requestHeaders(),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("The user's email address"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("The user's password"),
                                fieldWithPath("confirmPassword").type(JsonFieldType.STRING).ignored()
                        )
                ))
                .andDo(print());
    }

    @Test
    public void 로그인_실패_존재하지_않는_이메일() {
        // given

        // when

        // then
    }

    @Test
    public void 로그인_실패_틀린_비밀번호() {
        // given

        // when

        // then
    }

    @Test
    public void 회원가입() {
        // given

        // when

        // then
    }
}
