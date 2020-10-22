package com.naru.katalk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.common.Response;
import com.naru.katalk.common.SuccessCode;
import com.naru.katalk.domain.MemberManager;
import com.naru.katalk.service.GuestService;

@RequiredArgsConstructor
@RestController
public class RegisterController {

    private final GuestService guestService;

    @PostMapping(path = "/users")
    public ResponseEntity<Response> register(@RequestBody final MemberManager memberManager) {
        guestService.register(memberManager);
        return new ResponseEntity<>(new Response(SuccessCode.SIGNED_UP), HttpStatus.CREATED);
    }
}
