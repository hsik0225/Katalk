package com.naru.katalk.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberManager {

    @Embedded
    private SignManager signManager;

    @Embedded
    private ProfileManager profileManager;
}
