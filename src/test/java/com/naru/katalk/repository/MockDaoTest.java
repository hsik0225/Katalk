package com.naru.katalk.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.naru.katalk.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;

/*
@DataJpaTest 하는 일
- configuring H2, an in-memory database
- setting Hibernate, Spring Data, and the DataSource
- performing an @EntityScan
- turning on SQL logging
 */
// 기본적으로 재아된 임베디드 데이터베이스를 사용한다. 실제값을 사용하려면 Replace.NONE
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MockDaoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void saveMember() {
        final Member member = Member.getTestInstance();
        final Member newMember = memberRepository.save(member);
        assertThat(newMember.getMemberId()).isNotNull();
        assertThat(member.getSignManager()).isEqualTo(newMember.getSignManager());
    }
}
