package com.naru.katalk.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    private MemberManager memberManager;

    public Member(final MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public void checkPassword(final SignManager signManager) {
        this.memberManager.getSignManager().checkPassword(signManager);
    }

    public void changePassword() {

    }

    public void changeUserName() {

    }

    public void changePicture() {

    }

    public void enterRoom() {

    }

    public void readMessage() {

    }

    public void sendMessage() {

    }

    public void exitRoom() {

    }
}
