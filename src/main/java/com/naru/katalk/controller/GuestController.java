package com.naru.katalk.controller;

import com.naru.katalk.domain.SignManager;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @PostMapping
    public void logIn(@RequestBody SignManager signManager) {
        guestService.login(signManager);
    }

    @PostMapping
    public void register(@RequestBody MemberManager memberManager) {
        guestService.register(memberManager);
    }
}
