package com.naru.katalk.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import com.naru.katalk.repository.MemberRepository;

@ServiceTest
public class LoginServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Autowired
    private RestDocumentationResultHandler restDocumentation;

    @Test
    public void loginTest() {

    }
}
