package com.naru.katalk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.common.ErrorCode;
import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.RegisterException;
import com.naru.katalk.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final MemberRepository memberRepository;

    public static final String EMAIL = "email";

    @Transactional
    public Member register(Member member) {
        SignManager signManager = member.getSignManager();
        String email = signManager.getEmail();
        if (isEmailExist(email)) {
            throw new RegisterException(EMAIL, email, ErrorCode.EMAIL_DUPLICATION);
        }

        signManager = SignManager.hashPassword(signManager);

        Member newMember = new Member(signManager, member.getProfileManager());
        return memberRepository.save(newMember);
    }

    private boolean isEmailExist(String email) {
        return memberRepository.findBySignManager_Email(email).isPresent();
    }
}
