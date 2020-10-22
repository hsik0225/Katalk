package com.naru.katalk.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter

// NULL 값은 데이터 바인딩하지 않는다
@JsonInclude(JsonInclude.Include.NON_NULL)

// `transient` 속성을 데이터 바인딩하지 않기 위해 데이터 매핑 법칙은 `Getter`가 아니라 필드로 변경한다
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
public class Response {

    protected transient final HttpStatus httpStatus;

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
}
