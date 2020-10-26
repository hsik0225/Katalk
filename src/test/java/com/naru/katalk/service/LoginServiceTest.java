package com.naru.katalk.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.LoginException;
import com.naru.katalk.repository.MemberRepository;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

public class LoginServiceTest extends ServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private Member member;

    @InjectMocks
    private LoginService loginService;

    private static final SignManager signManager = SignManager.getTestInstance();

    @Test
    public void 로그인_성공() {

        // given
        given(memberRepository.findBySignManager_Email(anyString()))
                .willReturn((Optional.of(member)));
        given(member.checkPassword(any())).willReturn(true);

        // when, then
        assertDoesNotThrow(() -> loginService.login(signManager));
    }

    @Test
    public void 로그인_실패_존재하지_않는_이메일() {

        // given
        willThrow(LoginException.class).given(memberRepository)
                .findBySignManager_Email(anyString());

        // when, then
        thenThrownBy(() -> loginService.login(signManager))
                .isExactlyInstanceOf(LoginException.class)
                .hasMessage(null);
    }

    @Test
    public void 로그인_실패_틀린_비밀번호() {

        // given
        given(memberRepository.findBySignManager_Email(anyString()))
                .willReturn(Optional.of(member));
        willThrow(LoginException.class).given(member).checkPassword(signManager);

        // when, then
        thenThrownBy(() -> loginService.login(signManager))
                .isExactlyInstanceOf(LoginException.class)
                .hasMessage(null);
    }
}
