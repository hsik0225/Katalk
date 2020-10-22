package com.naru.katalk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naru.katalk.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByMemberManager_SignManager_Email(final String email);
}
