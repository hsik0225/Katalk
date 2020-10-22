package com.naru.katalk.controller;

import com.naru.katalk.common.Response;
import com.naru.katalk.common.SuccessCode;
import com.naru.katalk.domain.SignManager;
import com.naru.katalk.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final GuestService guestService;

    @PostMapping(path = "/users/login")
    public ResponseEntity<Response> logIn(@RequestBody final SignManager signManager) {
        guestService.login(signManager);
        return new ResponseEntity<>(new Response(SuccessCode.LOGGED_IN), HttpStatus.OK);
    }
}

