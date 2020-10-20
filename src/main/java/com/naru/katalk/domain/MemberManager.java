package com.naru.katalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@Setter
public class MemberManager {

    @Embedded
    private SignManager signManager;

    @Embedded
    private ProfileManager profileManager;
}
