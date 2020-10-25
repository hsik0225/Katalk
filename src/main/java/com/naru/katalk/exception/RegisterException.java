package com.naru.katalk.exception;

import com.naru.katalk.common.ErrorCode;

public class RegisterException extends FieldException {

    public RegisterException(String field, String value, ErrorCode errorCode) {
        super(field, value, errorCode);
    }
}
