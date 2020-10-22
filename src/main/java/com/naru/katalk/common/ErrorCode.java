package com.naru.katalk.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode implements ResponseStatusCode {

    // Member
    EMAIL_DUPLICATION("해당 이메일로 가입된 아이디가 존재합니다", HttpStatus.CONFLICT),
    LOGIN_INPUT_INVALID("존재하지 않는 이메일이거나 비밀번호가 일치하지 않습니다", HttpStatus.UNAUTHORIZED);

    private final String message;

    private final HttpStatus httpStatus;

    ErrorCode(final String message, final HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
