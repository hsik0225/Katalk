package com.naru.katalk.controller;

import com.naru.katalk.domain.LoginInformation;
import com.naru.katalk.domain.MemberInformation;
import com.naru.katalk.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private GuestService guestService;

    @PostMapping
    public void logIn(@RequestBody LoginInformation loginInfo) {
        guestService.login(loginInfo);
    }

    @PostMapping
    public void register(@RequestBody MemberInformation memberInfo) {
        guestService.register(memberInfo);
    }
}
