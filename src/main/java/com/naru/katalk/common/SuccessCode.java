package com.naru.katalk.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SuccessCode implements ResponseStatusCode {

    // Member
    LOGGED_IN("로그인 성공", HttpStatus.CREATED);

    private final String message;

    private final int code;

    private final String statusText;

    SuccessCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.code = httpStatus.value();
        this.statusText = httpStatus.getReasonPhrase();
    }
}
