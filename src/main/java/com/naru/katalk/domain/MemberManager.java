package com.naru.katalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class MemberManager {

    private SignManager signManager;

    private ProfileManager profileManager;
}
