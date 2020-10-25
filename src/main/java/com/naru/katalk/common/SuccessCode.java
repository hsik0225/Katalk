package com.naru.katalk.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum SuccessCode implements ResponseStatusCode {

    // Member
    LOGGED_IN(HttpStatus.OK, "로그인 성공"),
    SIGNED_UP(HttpStatus.CREATED, "회원가입 성공");

    private final HttpStatus httpStatus;

    private final String message;

    SuccessCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
