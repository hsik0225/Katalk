package com.naru.katalk.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import com.naru.katalk.common.ErrorCode;
import com.naru.katalk.common.ErrorResponse;
import com.naru.katalk.exception.LoginException;
import com.naru.katalk.exception.RegisterException;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(LoginException.class)
    protected ResponseEntity<ErrorResponse> handleLoginException() {
        return handleExceptionClass(LoginException.class,
                ErrorResponse.from(ErrorCode.LOGIN_INPUT_INVALID));
    }

    @ExceptionHandler(RegisterException.class)
    protected ResponseEntity<ErrorResponse> handleRegisterException(RegisterException e) {
        return handleExceptionClass(RegisterException.class,
                ErrorResponse.from(e));
    }

    private static ResponseEntity<ErrorResponse> handleExceptionClass(
            final Class<? extends Throwable> exception, final ErrorResponse errorResponse) {
        log.error("handle" + exception.getName(), exception);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
