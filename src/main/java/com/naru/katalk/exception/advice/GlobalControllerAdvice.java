package com.naru.katalk.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

import com.naru.katalk.common.ErrorCode;
import com.naru.katalk.common.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return handleExceptionClass(MethodArgumentNotValidException.class,
                ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult()));
    }

    private static ResponseEntity<ErrorResponse> handleExceptionClass(
            final Class<? extends Throwable> exception, final ErrorResponse errorResponse) {
        log.error("handle" + exception.getName(), exception);
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
