package com.naru.katalk.repository;

import com.naru.katalk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByMemberManager_SignManager_Email(final String email);
}
