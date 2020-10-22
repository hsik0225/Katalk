package com.naru.katalk.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode implements ResponseStatusCode {

    // Member
    LOGGED_IN("로그인 성공", HttpStatus.OK),
    SIGNED_UP("회원가입 성공", HttpStatus.CREATED);

    private final String message;

    private final HttpStatus httpStatus;

    SuccessCode(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
