package com.naru.katalk.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;

// NULL 값은 데이터 바인딩하지 않는다
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Response {

    protected final int statusCode;

    protected final String statusText;

    protected final String message;

    protected final Object body;

    public Response(HttpStatus httpStatus, String message, Object body) {
        this.statusCode = httpStatus.value();
        this.statusText = httpStatus.getReasonPhrase();
        this.message = message;
        this.body = body;
    }
}
