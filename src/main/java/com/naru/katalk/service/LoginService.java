package com.naru.katalk.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.LoginException;
import com.naru.katalk.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public void login(final SignManager signManager) {
        final Member member = findMemberByEmail(signManager);

        /*
        도메인 안에서 예외 처리를 할 수도 있다.
        하지만 그렇게 하면 예외를 LoginException 만 던질 수 있다
        만약 비밀번호를 변경할 때 비밀번호가 틀렸는데,
        존재하지 않는 아이디거나 비밀번호가 틀렸습니다
        라고 문구가 나오면 이상할 것이다
         */
        if (!member.checkPassword(signManager)) {
            throw new LoginException();
        }
    }

    private Member findMemberByEmail(final SignManager signManager) {
        return memberRepository
                .findBySignManager_Email(signManager.getEmail()).orElseThrow(LoginException::new);
    }
}
