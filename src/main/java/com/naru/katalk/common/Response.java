package com.naru.katalk.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;

// NULL 값은 데이터 바인딩하지 않는다
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Response {

    protected HttpStatus httpStatus;

    protected final String message;

    protected final Object body;

    protected Response(final ResponseStatusCode code, Object body) {
        this.httpStatus = code.getHttpStatus();
        this.message = code.getMessage();
        this.body = body;
    }

    protected int getHttpStatusCode() {
        return httpStatus.value();
    }

    protected String getHttpStatusText() {
        return httpStatus.getReasonPhrase();
    }

    protected String getMessage() {
        return message;
    }

    protected Object getBody() {
        return body;
    }
}
