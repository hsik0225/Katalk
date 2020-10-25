package com.naru.katalk.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode implements ResponseStatusCode {

    // Member
    EMAIL_DUPLICATION(HttpStatus.CONFLICT, "회원가입 실패", "해당 이메일로 가입된 아이디가 이미 존재합니다"),
    LOGIN_INPUT_INVALID(HttpStatus.UNAUTHORIZED, "로그인 실패", "존재하지 않는 이메일이거나 비밀번호가 일치하지 않습니다");

    private final HttpStatus httpStatus;

    private final String message;

    private final String reason;

    ErrorCode(HttpStatus httpStatus, String message, String reason) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.reason = reason;
    }
}
