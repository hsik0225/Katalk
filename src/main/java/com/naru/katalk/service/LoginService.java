package com.naru.katalk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        member.checkPassword(signManager);
    }

    private Member findMemberByEmail(final SignManager signManager) {
        return memberRepository
                .findBySignManager_Email(signManager.getEmail()).orElseThrow(LoginException::new);
    }
}
