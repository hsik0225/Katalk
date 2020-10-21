package com.naru.katalk.exception;

public class LoginException extends RuntimeException {

    public LoginException() {
        super("존재하지 않는 이메일이거나 비밀번호가 일치하지 않습니다");
    }
}
