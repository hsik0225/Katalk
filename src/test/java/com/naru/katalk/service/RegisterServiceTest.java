package com.naru.katalk.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.naru.katalk.common.ErrorCode;
import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.RegisterException;
import com.naru.katalk.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class RegisterServiceTest extends ServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private RegisterService registerService;

    private static final Member member = Member.getTestInstance();

    public static final SignManager signManager = member.getSignManager();

    public static final ProfileManager profileManager = member.getProfileManager();

    @Test
    public void 회원가입_성공() {

        // given
        given(memberRepository.findBySignManager_Email(anyString())).willReturn(Optional.empty());
        given(memberRepository.save(any())).willReturn(member);

        // when
        Member newMember = registerService.register(member);

        // then
        then(newMember)
                .isNotNull()
                .hasFieldOrPropertyWithValue("signManager", signManager)
                .hasFieldOrPropertyWithValue("profileManager", profileManager);

        then(newMember.getSignManager())
                .isNotNull()
                .isEqualTo(signManager);

        then(newMember.getProfileManager())
                .isNotNull()
                .isEqualTo(profileManager);
    }

    @Test
    public void 회원가입_실패_중복된_이메일() {

        final String email = signManager.getEmail();

        // given
        given(memberRepository.findBySignManager_Email(email)).willReturn(Optional.of(member));

        // when, then
        assertThatThrownBy(() -> registerService.register(member))
                .isExactlyInstanceOf(RegisterException.class)
                .hasFieldOrPropertyWithValue("field", "email")
                .hasFieldOrPropertyWithValue("value", email)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.EMAIL_DUPLICATION);
    }
}