package com.naru.katalk.exception;

import lombok.Getter;

import com.naru.katalk.common.ErrorCode;

@Getter
// 필드를 갖는 예외 처리들의 커스텀 조상 클래스
public abstract class FieldException extends RuntimeException {

    private final String field;

    private final String value;

    private final ErrorCode errorCode;

    protected FieldException(String field, String value, ErrorCode errorCode) {
        this.field = field;
        this.value = value;
        this.errorCode = errorCode;
    }
}
