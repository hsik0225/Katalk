package com.naru.katalk.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileManager {

    private static final String CHAT_NAME = "naru";

    private static final String PICTURE = "s3.com";

    // 대화명
    private String chatName;

    // 사진 URL
    private String picture;

    public static ProfileManager getTestInstance() {
        return ProfileManager
                .builder()
                .chatName(CHAT_NAME)
                .picture(PICTURE)
                .build();
    }
}
