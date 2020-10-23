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

    // 대화명
    private String chatName;

    // 사진 URL
    private String picture;
}
