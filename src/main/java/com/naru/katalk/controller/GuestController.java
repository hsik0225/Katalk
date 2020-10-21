package com.naru.katalk.controller;

import com.naru.katalk.common.Response;
import com.naru.katalk.common.SuccessCode;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class GuestController {

    private final GuestService guestService;

    @PostMapping(path = "/login")
    public ResponseEntity<Response> logIn(@RequestBody SignManager signManager) {
        guestService.login(signManager);
        return new ResponseEntity<>(new Response(SuccessCode.LOGGED_IN), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody MemberManager memberManager) {
        guestService.register(memberManager);
        return new ResponseEntity<>(new Response(SuccessCode.SIGNED_UP), HttpStatus.CREATED);
    }
}

