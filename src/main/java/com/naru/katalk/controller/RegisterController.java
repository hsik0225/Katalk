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
import com.naru.katalk.domain.Member;
import com.naru.katalk.service.GuestService;

@RequiredArgsConstructor
@RestController
public class RegisterController {

    private final GuestService guestService;

    @PostMapping(path = "/users")
    public ResponseEntity<SuccessResponse> register(
            @RequestBody @Valid final Member member) {
        guestService.register(member);
        return new ResponseEntity<>(SuccessResponse.from(SuccessCode.SIGNED_UP),
                HttpStatus.CREATED);
    }
}
