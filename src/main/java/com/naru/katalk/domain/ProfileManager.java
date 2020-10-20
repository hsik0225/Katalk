package com.naru.katalk.domain;

import javax.persistence.Embeddable;

@Embeddable
public class ProfileManager {

    // 대화명
    private String userName;

    // 사진 URL
    private String picture;
}
