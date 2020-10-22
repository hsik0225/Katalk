package com.naru.katalk.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileManager {

    // 대화명
    private String userName;

    // 사진 URL
    private String picture;
}
