package com.naru.katalk.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    @Valid
    private SignManager signManager;

    @Embedded
    @Valid
    private ProfileManager profileManager;

    @Builder
    public Member(final SignManager signManager, final ProfileManager profileManager) {
        this.signManager = signManager;
        this.profileManager = profileManager;
    }

    public boolean checkPassword(final SignManager signManager) {
        return this.signManager.checkPassword(signManager);
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

    public static Member getTestInstance() {
        SignManager signManager = SignManager.getTestInstance();
        ProfileManager profileManager = ProfileManager.getTestInstance();
        return new Member(signManager, profileManager);
    }
}
