package com.naru.katalk.service;

import com.naru.katalk.domain.Member;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.exception.DuplicateEmailException;
import com.naru.katalk.exception.LoginException;
import com.naru.katalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GuestService {

    private final MemberRepository memberRepository;

    public void login(SignManager signManager) {
        Member member = findMemberByEmail(signManager);
        member.checkPassword(signManager);
    }

    @Transactional
    public void register(MemberManager memberManager) {
        Member member = new Member(memberManager);
        SignManager signManager = member.getMemberManager().getSignManager();
        checkDuplicateEmail(signManager);
        signManager.hashPassword();
        memberRepository.save(member);
    }

    private Optional<Member> findOptMemberByEmail(SignManager signManager) {
        return memberRepository
                .findMemberByMemberManager_SignManager_Email(signManager.getEmail());
    }

    private Member findMemberByEmail(SignManager signManager) {
        return findOptMemberByEmail(signManager).orElseThrow(LoginException::new);
    }

    private void checkDuplicateEmail(SignManager signManager) {
        if (findOptMemberByEmail(signManager).isPresent()) {
            throw new DuplicateEmailException("해당 이메일로 가입된 아이디가 존재합니다");
        }
    }
}
