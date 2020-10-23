package com.naru.katalk.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.domain.ProfileManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.DuplicateEmailException;
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
    public void register(MemberManager memberManager) {
        SignManager signManager = memberManager.getSignManager();
        checkDuplicateEmail(signManager);

        signManager = SignManager.hashPassword(signManager);
        memberManager = memberManager.create(signManager);

        Member member = new Member(memberManager);
        memberRepository.save(member);
    }

    private Optional<Member> findOptMemberByEmail(final SignManager signManager) {
        return memberRepository
                .findMemberByMemberManager_SignManager_Email(signManager.getEmail());
    }

    private Member findMemberByEmail(final SignManager signManager) {
        return findOptMemberByEmail(signManager).orElseThrow(LoginException::new);
    }

    private void checkDuplicateEmail(final SignManager signManager) {
        if (findOptMemberByEmail(signManager).isPresent()) {
            throw new DuplicateEmailException("해당 이메일로 가입된 아이디가 존재합니다");
        }
    }
}
