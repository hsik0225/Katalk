package com.naru.katalk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileManager {

    // 대화명
    private String userName;

    // 사진 URL
    private String picture;
}
