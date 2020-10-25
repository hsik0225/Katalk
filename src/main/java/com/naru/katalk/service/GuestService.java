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
public class GuestService {

    private final MemberRepository memberRepository;

    public void login(final SignManager signManager) {
        final Member member = findMemberByEmail(signManager);
        member.checkPassword(signManager);
    }

    @Transactional
    public void register(Member member) {
        SignManager signManager = member.getSignManager();
        signManager = SignManager.hashPassword(signManager);

        Member newMember = new Member(signManager, member.getProfileManager());
        memberRepository.save(newMember);
    }

    private Member findMemberByEmail(final SignManager signManager) {
        return memberRepository
                .findBySignManager_Email(signManager.getEmail()).orElseThrow(LoginException::new);
    }
}
