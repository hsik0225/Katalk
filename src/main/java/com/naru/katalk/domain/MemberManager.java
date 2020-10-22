package com.naru.katalk.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberManager {

    @Embedded
    private SignManager signManager;

    @Embedded
    private ProfileManager profileManager;
}
