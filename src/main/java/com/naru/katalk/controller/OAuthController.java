package com.naru.katalk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.naru.katalk.common.SuccessCode;
import com.naru.katalk.common.SuccessResponse;

@RequiredArgsConstructor
@RestController
public class OAuthController {

    @PostMapping(path = "/users/oauth")
    public ResponseEntity<SuccessResponse> oauthLogIn() {
        return new ResponseEntity<>(SuccessResponse.from(SuccessCode.LOGGED_IN), HttpStatus.OK);
    }
}
