package com.naru.katalk.common;

import org.springframework.http.HttpStatus;

import javax.persistence.Transient;

public class Response {

    @Transient
    protected final HttpStatus httpStatus;

    protected final String message;

    protected final String statusCodeAndText;

    protected Object body;

    public Response(final ResponseStatusCode code) {
        this.message = code.getMessage();
        this.httpStatus = code.getHttpStatus();
        this.statusCodeAndText = this.httpStatus.value() + " " + this.httpStatus.getReasonPhrase();
    }

    public Response(final ResponseStatusCode code, final Object body) {
        this(code);
        this.body = body;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
