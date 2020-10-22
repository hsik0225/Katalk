package com.naru.katalk.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter

// NULL 값은 데이터 바인딩하지 않는다
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    // httpStatus를 데이터 매핑에서 제외
    @JsonIgnore
    protected final HttpStatus httpStatus;

    protected final String message;

    // 여기에선 httpStatus와 구분하기 위하여 statusCodeAndText라는 변수명을 사용하지만
    // 응답에선 status가 더 명확하므로 JsonPropert로 `status`로 설정
    @JsonProperty(value = "status")
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
