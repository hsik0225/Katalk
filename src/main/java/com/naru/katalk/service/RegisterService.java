package com.naru.katalk.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(Member member) {
        SignManager signManager = member.getSignManager();
        signManager = SignManager.hashPassword(signManager);

        Member newMember = new Member(signManager, member.getProfileManager());
        memberRepository.save(newMember);
    }
}
