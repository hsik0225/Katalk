package com.naru.katalk.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private MemberManager memberManager;

    public Member(MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public void checkPassword(SignManager signManager) {
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
