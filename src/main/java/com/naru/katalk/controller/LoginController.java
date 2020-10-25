package com.naru.katalk.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.common.SuccessCode;
import com.naru.katalk.common.SuccessResponse;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.GuestService;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final GuestService guestService;

    @PostMapping(path = "/users/login")
    public ResponseEntity<SuccessResponse> logIn(
            @RequestBody @Valid final SignManager signManager) {
        guestService.login(signManager);
        return new ResponseEntity<>(SuccessResponse.from(SuccessCode.LOGGED_IN), HttpStatus.OK);
    }
}

